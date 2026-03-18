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

  private volatile UserProfileDao _userProfileDao;

  private volatile ConversationDao _conversationDao;

  private volatile ActionHistoryDao _actionHistoryDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_usage_events` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `packageName` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `durationMillis` INTEGER, `isNightUsage` INTEGER NOT NULL, `stressScore` INTEGER NOT NULL, `aiResponse` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_personalities` (`packageName` TEXT NOT NULL, `voiceStyle` TEXT NOT NULL, `isEnabled` INTEGER NOT NULL, `pitch` REAL NOT NULL, `speechRate` REAL NOT NULL, PRIMARY KEY(`packageName`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_profile` (`id` INTEGER NOT NULL, `userName` TEXT NOT NULL, `preferredName` TEXT NOT NULL, `dailyRoutine` TEXT NOT NULL, `lastUpdated` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `conversation_memory` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `timestamp` INTEGER NOT NULL, `userSaid` TEXT NOT NULL, `akvaReplied` TEXT NOT NULL, `context` TEXT NOT NULL, `actionTaken` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `action_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `timestamp` INTEGER NOT NULL, `action` TEXT NOT NULL, `target` TEXT NOT NULL, `result` TEXT NOT NULL, `success` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9c88b6968b31bd30b30882f84a55518e')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `app_usage_events`");
        db.execSQL("DROP TABLE IF EXISTS `app_personalities`");
        db.execSQL("DROP TABLE IF EXISTS `user_profile`");
        db.execSQL("DROP TABLE IF EXISTS `conversation_memory`");
        db.execSQL("DROP TABLE IF EXISTS `action_history`");
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
        _columnsAppUsageEvents.put("aiResponse", new TableInfo.Column("aiResponse", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
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
        final HashMap<String, TableInfo.Column> _columnsUserProfile = new HashMap<String, TableInfo.Column>(5);
        _columnsUserProfile.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("userName", new TableInfo.Column("userName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("preferredName", new TableInfo.Column("preferredName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("dailyRoutine", new TableInfo.Column("dailyRoutine", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserProfile = new TableInfo("user_profile", _columnsUserProfile, _foreignKeysUserProfile, _indicesUserProfile);
        final TableInfo _existingUserProfile = TableInfo.read(db, "user_profile");
        if (!_infoUserProfile.equals(_existingUserProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "user_profile(com.varun.akva.data.UserProfile).\n"
                  + " Expected:\n" + _infoUserProfile + "\n"
                  + " Found:\n" + _existingUserProfile);
        }
        final HashMap<String, TableInfo.Column> _columnsConversationMemory = new HashMap<String, TableInfo.Column>(6);
        _columnsConversationMemory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversationMemory.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversationMemory.put("userSaid", new TableInfo.Column("userSaid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversationMemory.put("akvaReplied", new TableInfo.Column("akvaReplied", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversationMemory.put("context", new TableInfo.Column("context", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConversationMemory.put("actionTaken", new TableInfo.Column("actionTaken", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysConversationMemory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesConversationMemory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoConversationMemory = new TableInfo("conversation_memory", _columnsConversationMemory, _foreignKeysConversationMemory, _indicesConversationMemory);
        final TableInfo _existingConversationMemory = TableInfo.read(db, "conversation_memory");
        if (!_infoConversationMemory.equals(_existingConversationMemory)) {
          return new RoomOpenHelper.ValidationResult(false, "conversation_memory(com.varun.akva.data.ConversationMemory).\n"
                  + " Expected:\n" + _infoConversationMemory + "\n"
                  + " Found:\n" + _existingConversationMemory);
        }
        final HashMap<String, TableInfo.Column> _columnsActionHistory = new HashMap<String, TableInfo.Column>(6);
        _columnsActionHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActionHistory.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActionHistory.put("action", new TableInfo.Column("action", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActionHistory.put("target", new TableInfo.Column("target", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActionHistory.put("result", new TableInfo.Column("result", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActionHistory.put("success", new TableInfo.Column("success", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysActionHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesActionHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoActionHistory = new TableInfo("action_history", _columnsActionHistory, _foreignKeysActionHistory, _indicesActionHistory);
        final TableInfo _existingActionHistory = TableInfo.read(db, "action_history");
        if (!_infoActionHistory.equals(_existingActionHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "action_history(com.varun.akva.data.ActionHistory).\n"
                  + " Expected:\n" + _infoActionHistory + "\n"
                  + " Found:\n" + _existingActionHistory);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9c88b6968b31bd30b30882f84a55518e", "efd6dc57318c6c2f2a02241b6435a5ef");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "app_usage_events","app_personalities","user_profile","conversation_memory","action_history");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `app_usage_events`");
      _db.execSQL("DELETE FROM `app_personalities`");
      _db.execSQL("DELETE FROM `user_profile`");
      _db.execSQL("DELETE FROM `conversation_memory`");
      _db.execSQL("DELETE FROM `action_history`");
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
    _typeConvertersMap.put(UserProfileDao.class, UserProfileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ConversationDao.class, ConversationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ActionHistoryDao.class, ActionHistoryDao_Impl.getRequiredConverters());
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
  public AppUsageDao usageDao() {
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
  public AppPersonalityDao personalityDao() {
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

  @Override
  public UserProfileDao userProfileDao() {
    if (_userProfileDao != null) {
      return _userProfileDao;
    } else {
      synchronized(this) {
        if(_userProfileDao == null) {
          _userProfileDao = new UserProfileDao_Impl(this);
        }
        return _userProfileDao;
      }
    }
  }

  @Override
  public ConversationDao conversationDao() {
    if (_conversationDao != null) {
      return _conversationDao;
    } else {
      synchronized(this) {
        if(_conversationDao == null) {
          _conversationDao = new ConversationDao_Impl(this);
        }
        return _conversationDao;
      }
    }
  }

  @Override
  public ActionHistoryDao actionHistoryDao() {
    if (_actionHistoryDao != null) {
      return _actionHistoryDao;
    } else {
      synchronized(this) {
        if(_actionHistoryDao == null) {
          _actionHistoryDao = new ActionHistoryDao_Impl(this);
        }
        return _actionHistoryDao;
      }
    }
  }
}
