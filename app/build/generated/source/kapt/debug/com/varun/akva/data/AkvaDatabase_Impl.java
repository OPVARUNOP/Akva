package com.varun.akva.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AkvaDatabase_Impl extends AkvaDatabase {
  private volatile AppUsageDao _appUsageDao;

  private volatile AppPersonalityDao _appPersonalityDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_usage_events` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `packageName` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `durationMillis` INTEGER, `isNightUsage` INTEGER NOT NULL, `stressScore` INTEGER NOT NULL, `spokenText` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_personalities` (`packageName` TEXT NOT NULL, `voiceStyle` TEXT NOT NULL, `isEnabled` INTEGER NOT NULL, `pitch` REAL NOT NULL, `speechRate` REAL NOT NULL, PRIMARY KEY(`packageName`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6ddadf5b89bc52931c8769469f87378a')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `app_usage_events`");
        db.execSQL("DROP TABLE IF EXISTS `app_personalities`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAppUsageEvents = new HashMap<String, TableInfo.Column>(7);
        _columnsAppUsageEvents.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsageEvents.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsageEvents.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsageEvents.put("durationMillis", new TableInfo.Column("durationMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsageEvents.put("isNightUsage", new TableInfo.Column("isNightUsage", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsageEvents.put("stressScore", new TableInfo.Column("stressScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsageEvents.put("spokenText", new TableInfo.Column("spokenText", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppUsageEvents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppUsageEvents = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppUsageEvents = new TableInfo("app_usage_events", _columnsAppUsageEvents, _foreignKeysAppUsageEvents, _indicesAppUsageEvents);
        final TableInfo _existingAppUsageEvents = TableInfo.read(db, "app_usage_events");
        if (!_infoAppUsageEvents.equals(_existingAppUsageEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "app_usage_events(com.varun.akva.data.AppUsageEvent).\n"
                  + " Expected:\n" + _infoAppUsageEvents + "\n"
                  + " Found:\n" + _existingAppUsageEvents);
        }
        final HashMap<String, TableInfo.Column> _columnsAppPersonalities = new HashMap<String, TableInfo.Column>(5);
        _columnsAppPersonalities.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPersonalities.put("voiceStyle", new TableInfo.Column("voiceStyle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPersonalities.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPersonalities.put("pitch", new TableInfo.Column("pitch", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPersonalities.put("speechRate", new TableInfo.Column("speechRate", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppPersonalities = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppPersonalities = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppPersonalities = new TableInfo("app_personalities", _columnsAppPersonalities, _foreignKeysAppPersonalities, _indicesAppPersonalities);
        final TableInfo _existingAppPersonalities = TableInfo.read(db, "app_personalities");
        if (!_infoAppPersonalities.equals(_existingAppPersonalities)) {
          return new RoomOpenHelper.ValidationResult(false, "app_personalities(com.varun.akva.data.AppPersonality).\n"
                  + " Expected:\n" + _infoAppPersonalities + "\n"
                  + " Found:\n" + _existingAppPersonalities);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6ddadf5b89bc52931c8769469f87378a", "c32a24ca1fb2246fe67e7adb567a0e17");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "app_usage_events","app_personalities");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `app_usage_events`");
      _db.execSQL("DELETE FROM `app_personalities`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AppUsageDao.class, AppUsageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AppPersonalityDao.class, AppPersonalityDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AppUsageDao appUsageDao() {
    if (_appUsageDao != null) {
      return _appUsageDao;
    } else {
      synchronized(this) {
        if(_appUsageDao == null) {
          _appUsageDao = new AppUsageDao_Impl(this);
        }
        return _appUsageDao;
      }
    }
  }

  @Override
  public AppPersonalityDao appPersonalityDao() {
    if (_appPersonalityDao != null) {
      return _appPersonalityDao;
    } else {
      synchronized(this) {
        if(_appPersonalityDao == null) {
          _appPersonalityDao = new AppPersonalityDao_Impl(this);
        }
        return _appPersonalityDao;
      }
    }
  }
}
