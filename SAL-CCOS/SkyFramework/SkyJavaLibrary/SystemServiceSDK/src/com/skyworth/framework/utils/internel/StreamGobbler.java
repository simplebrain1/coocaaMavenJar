/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-10-22         thinkhwa
 */

package com.skyworth.framework.utils.internel;

import com.skyworth.framework.utils.internel.log.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

public class StreamGobbler {
    public static final String ERRTYPE = "STDERR";
    public static final String STDTYPE = "STDOUT";
    Process mProcess;
    private int mDelay = 5;
    Worker processThread;

    public StreamGobbler(Process process) {
        mProcess = process;
        processThread = new Worker();

    }

    /**
     * <p>
     * Description:Java多线程之Callable接口的实现
     * </p>
     * <p>
     * * Callable 和 Future接口 Callable是类似于Runnable的接口，实现Callable接口
     * 的类和实现Runnable的类都是可被其它线程执行的任务。 Callable和Runnable有几点不同：
     * （1）Callable规定的方法是call()，而Runnable规定的方法是run()。
     * （2）Callable的任务执行后可返回值，而Runnable的任务是不能返回值的。
     * （3）call()方法可抛出异常，而run()方法是不能抛出异常的。 （4）运行Callable任务可拿到一个Future对象， Future
     * 表示异步计算的结果。它提供了检查计算是否完成的方法， 以等待计算的完成，并检索计算的结果。
     * 通过Future对象可了解任务执行情况，可取消任务的执行，还可获取任务执行的结果。
     * </p>
     *
     * @author thinkhwa
     * @ClassName MyCallBale
     * @date 2013-10-22
     */
    public class MyCallBale implements Callable<String> {
        public MyCallBale() {
        }

        @Override
        public String call() throws Exception {
            try {
                InputStream input = mProcess.getInputStream();
                InputStreamReader isr = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    Logger.w(STDTYPE + ">" + line);
                }

                InputStream errinput = mProcess.getErrorStream();
                InputStreamReader errisr = new InputStreamReader(errinput);
                BufferedReader errbr = new BufferedReader(errisr);
                String errline = null;
                while ((errline = errbr.readLine()) != null) {
                    Logger.w(ERRTYPE + ">" + errline);
                }

                return line;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return "";
        }
    }

    public void setDelaySecond(int second) {
        this.mDelay = second;
    }

    /**
     * 概述：// flush all output avoid blocking,<br/>
     * create a new thread avoid block<br/>
     *
     * @date 2013-10-22
     */
    public int start() throws InterruptedException, TimeoutException {
        processThread.start();
        try {
            processThread.join(mDelay * 1000);
            if (processThread.exit != null) {
                return processThread.exit;
            } else {
                throw new TimeoutException();
            }
        } catch (InterruptedException ex) {
            processThread.interrupt();
            Thread.currentThread().interrupt();
            throw ex;
        }
    }

    private class Worker extends Thread {
        private Integer exit;

        private Worker() {
        }

        public void run() {
            try {
                InputStream input = mProcess.getInputStream();
                InputStreamReader isr = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    Logger.w(STDTYPE + ">" + line);
                }

                InputStream errinput = mProcess.getErrorStream();
                InputStreamReader errisr = new InputStreamReader(errinput);
                BufferedReader errbr = new BufferedReader(errisr);
                String errline = null;
                while ((errline = errbr.readLine()) != null) {
                    Logger.w(ERRTYPE + ">" + errline);
                }

                try {
                    exit = mProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
