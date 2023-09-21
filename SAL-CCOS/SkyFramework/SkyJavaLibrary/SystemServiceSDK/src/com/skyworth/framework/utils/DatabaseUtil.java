/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-10-17         thinkhwa
 */

package com.skyworth.framework.utils;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.skyworth.framework.utils.internel.log.Logger;

/**
 * 数据库常用方法类
 */
public class DatabaseUtil {
    public interface QueryHandler {
        public Object onResult(Cursor cursor, Object obj);
    }

    private String dataBasePath = null;
    public final static String FILED_TYPE_STRING = "TEXT";
    public final static String FILED_TYPE_INT = "INTEGER";
    public final static String FILED_TYPE_NUMERIC = "NUMERIC";
    public final static String FILED_TYPE_REAL = "REAL";
    public final static String FILED_TYPE_NONE = "NONE";
    public final static String FILED_TYPE_LONG = "LONG";

    /**
     * 概述：设置数据库路径
     *
     * @param dbPath
     */
    public DatabaseUtil(String dbPath) {
        dataBasePath = dbPath;
    }

    /**
     * 概述：重置数据库路径 <br/>
     *
     * @param dbPath void
     * @date 2013-10-17
     */
    public void resetDBPath(String dbPath) {
        dataBasePath = dbPath;
    }

    /**
     * 概述：创建一tableName的数据表 <br/>
     *
     * @param tableName       数据表的名称
     * @param columnsAndTypes 数据表列名称以及类型数组
     * @date 2013-10-17
     */
    public synchronized void createTable(String tableName, String[][] columnsAndTypes) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dataBasePath, null);
        String tableColumns = "";
        for (int i = 0; i < columnsAndTypes.length; i++) {
            tableColumns = tableColumns + columnsAndTypes[i][0] + " " + columnsAndTypes[i][1];
            if (i < (columnsAndTypes.length - 1)) {
                tableColumns += ",";
            }
        }
        createTable(tableName, tableColumns);
        db.close();
    }

    /**
     * 概述：创建以customColumns为参数的tableName表<br/>
     *
     * @param tableName     数据库表的名字
     * @param customColumns 数据库表参数
     * @date 2013-10-17
     */
    public synchronized void createTable(String tableName, String customColumns) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dataBasePath, null);
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + customColumns + ");";
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Logger.error(e.toString());
        } finally {
            db.close();
        }
    }

    /**
     * 判断某张表是否存在
     *
     * @param tabName 表名
     * @return
     */
    public synchronized boolean isTableExist(String tabName) {
        boolean result = false;
        if (tabName == null) {
            return false;
        }
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dataBasePath, null);
        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"
                    + tabName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            // shendongsheng,2015,0718
            //to solve error:Finalizing a Cursor that has not been deactivated or closed
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return result;
    }

    /**
     * 概述：向tableName的数据库表中插入一条数据values <br/>
     *
     * @param tableName 数据库表
     * @param values    插入的数据表
     * @date 2013-10-17
     */
    public synchronized void insert(String tableName, Object[] values) {
        SQLiteDatabase db = getDatabaseHandler(SQLiteDatabase.OPEN_READWRITE);
        if (db == null) {
            return;
        }
        if (values.length <= 0) {
            db.close();
            return;
        }
        String insertValuesString = "";
        for (Object obj : values) {
            if (obj == null) {
                insertValuesString += "null";
            } else if (obj.getClass().getName().equals("java.lang.String")) {
                String value = obj.toString();
                value = value.replace("'", "''");
                insertValuesString += "'" + value + "'";
            } else {
                insertValuesString += obj.toString();
            }
            insertValuesString += ",";
        }
        insertValuesString = insertValuesString.substring(0, insertValuesString.length() - 1);
        String sql = "INSERT INTO " + tableName + " VALUES(" + insertValuesString + ");";
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Logger.error(e.toString());
        } finally {
            db.close();
        }
    }

    /**
     * 概述：向tableName的数据库表中插入一条数据values，如果存在则忽略此次插入<br/>
     *
     * @param tableName 数据库表
     * @param values    插入的数据表
     * @date 2013-10-17
     */
    public synchronized void insert_ignore(String tableName, Object[] values) {
        SQLiteDatabase db = getDatabaseHandler(SQLiteDatabase.OPEN_READWRITE);
        if (db == null) {
            return;
        }
        if (values.length <= 0) {
            db.close();
            return;
        }
        String insertValuesString = "";
        for (Object obj : values) {
            if (obj == null) {
                insertValuesString += "null";
            } else if (obj.getClass().getName().equals("java.lang.String")) {
                String value = obj.toString();
                value = value.replace("'", "''");
                insertValuesString += "'" + value + "'";
            } else {
                insertValuesString += obj.toString();
            }
            insertValuesString += ",";
        }
        insertValuesString = insertValuesString.substring(0, insertValuesString.length() - 1);
        String sql = "INSERT OR IGNORE INTO " + tableName + " VALUES(" + insertValuesString + ");";
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            // Logger.error(e.toString());
        } finally {
            db.close();
        }
    }

    /**
     * 概述：向tableName的数据库表中插入一条数据values，如果存在则插入数据并替换原来的数据项<br/>
     *
     * @param tableName 数据库表
     * @param values    插入的数据表
     * @date 2013-10-17
     */
    public synchronized void insert_replace(String tableName, Object[] values) {
        // SQLiteDatabase.openDatabase(dataBasePath, null, SQLiteDatabase.OPEN_READWRITE);
        SQLiteDatabase db = getDatabaseHandler(SQLiteDatabase.OPEN_READWRITE);
        if (db == null) {
            return;
        }
        if (values.length <= 0) {
            db.close();
            return;
        }
        String insertValuesString = "";
        for (Object obj : values) {
            if (obj == null) {
                insertValuesString += "null";
            } else if (obj.getClass().getName().equals("java.lang.String")) {
                String value = obj.toString();
                value = value.replace("'", "''");
                insertValuesString += "'" + value + "'";
            } else {
                insertValuesString += obj.toString();
            }
            insertValuesString += ",";
        }
        insertValuesString = insertValuesString.substring(0, insertValuesString.length() - 1);
        String sql = "INSERT OR REPLACE INTO  " + tableName + " VALUES(" + insertValuesString
                + ");";
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Logger.error(e.toString());
        } finally {
            db.close();
        }
    }

    /**
     * 概述：执行传入的sql语句<br/>
     *
     * @param sql 表示SQL语句的字符串
     * @date 2013-10-22
     */
    public synchronized void exec(String sql) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dataBasePath, null);
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Logger.error(e.toString());
        } finally {
            db.close();
        }
    }

    /**
     * 概述：格式转换，将'替换成''<br/>
     *
     * @param value
     * @return String
     * @date 2013-10-22
     */
    public static String formatString(String value) {
        return value.replace("'", "''");
    }

    /**
     * 概述：对传入的sql参数执行查询命令，数据库为OPEN_READONLY<br/>
     *
     * @param sql     SQL 语句
     * @param obj
     * @param handler 回调接口
     * @return Object
     * @date 2013-10-22
     */
    public synchronized Object query(String sql, Object obj, QueryHandler handler) {
        // SQLiteDatabase.openDatabase(dataBasePath, null, SQLiteDatabase.OPEN_READONLY);
        SQLiteDatabase db = getDatabaseHandler(SQLiteDatabase.OPEN_READONLY);
        if (db == null) {
            return null;
        }
        try {
            if (handler != null) {
                Cursor cursor = null;
                try {
                    cursor = db.rawQuery(sql, null);
                } catch (Exception e) {
                    Logger.error(e.toString());
                    db.close();
                    return null;
                }
                Object result = null;
                // if (cursor.getCount() > 0)
                // {
                result = handler.onResult(cursor, obj);
                // }
                cursor.close();
                db.close();
                return result;
            }
        } catch (Exception e) {
            Logger.error(e.toString());
        } finally {
            db.close();
        }
        return null;
    }

    /**
     * 概述：对传入的sql参数执行查询命令，数据库为NO_LOCALIZED_COLLATORS<br/>
     *
     * @param sql
     * @param obj
     * @param handler
     * @return Object
     * @date 2013-10-22
     */
    public synchronized Object query2(String sql, Object obj, QueryHandler handler) {
        SQLiteDatabase db = getDatabaseHandler(SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        if (db == null) {
            return null;
        }

        try {
            if (handler != null) {
                Cursor cursor = null;
                try {
                    cursor = db.rawQuery(sql, null);
                } catch (Exception e) {
                    Logger.error(e.toString());
                    db.close();
                    return null;
                }
                Object result = null;
                if (cursor.getCount() > 0) {
                    result = handler.onResult(cursor, obj);
                }
                cursor.close();
                db.close();
                return result;
            }
        } catch (Exception e) {
            Logger.error(e.toString());
        } finally {
            db.close();
        }
        return null;
    }

    /**
     * 概述：<br/>
     *
     * @param table       the table to delete from
     * @param whereClause the optional WHERE clause to apply when deleting. Passing null will delete all
     *                    rows.
     * @param whereArgs
     * @return int 1表示删除表成功
     * @date 2013-10-22
     */
    public synchronized int deleteDb(String table, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getDatabaseHandler(SQLiteDatabase.OPEN_READWRITE);
        if (db == null) {
            return 1;
        }
        // SQLiteDatabase.openDatabase(dataBasePath, null, SQLiteDatabase.OPEN_READWRITE);
        try {
            db.delete(table, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return 1;
    }

    private SQLiteDatabase getDatabaseHandler(int flags) {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(dataBasePath, null, flags);
        } catch (SQLException e) {
            Logger.e("SkyDBUtil, SQLException = " + e.getMessage());
        }
        return db;
    }
}
