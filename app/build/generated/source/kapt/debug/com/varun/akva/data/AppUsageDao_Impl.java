package com.varun.akva.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppUsageDao_Impl implements AppUsageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppUsageEvent> __insertionAdapterOfAppUsageEvent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  public AppUsageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppUsageEvent = new EntityInsertionAdapter<AppUsageEvent>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `app_usage_events` (`id`,`packageName`,`timestamp`,`durationMillis`,`isNightUsage`,`stressScore`,`spokenText`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppUsageEvent entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getPackageName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPackageName());
        }
        statement.bindLong(3, entity.getTimestamp());
        if (entity.getDurationMillis() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getDurationMillis());
        }
        final int _tmp = entity.isNightUsage() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getStressScore());
        if (entity.getSpokenText() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getSpokenText());
        }
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM app_usage_events WHERE timestamp < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertUsageEvent(final AppUsageEvent event,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppUsageEvent.insert(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOlderThan(final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOlderThan.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOlderThan.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getRecentUsage(final long since,
      final Continuation<? super List<AppUsageEvent>> $completion) {
    final String _sql = "SELECT * FROM app_usage_events WHERE timestamp >= ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEvent>>() {
      @Override
      @NonNull
      public List<AppUsageEvent> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDurationMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMillis");
          final int _cursorIndexOfIsNightUsage = CursorUtil.getColumnIndexOrThrow(_cursor, "isNightUsage");
          final int _cursorIndexOfStressScore = CursorUtil.getColumnIndexOrThrow(_cursor, "stressScore");
          final int _cursorIndexOfSpokenText = CursorUtil.getColumnIndexOrThrow(_cursor, "spokenText");
          final List<AppUsageEvent> _result = new ArrayList<AppUsageEvent>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEvent _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDurationMillis;
            if (_cursor.isNull(_cursorIndexOfDurationMillis)) {
              _tmpDurationMillis = null;
            } else {
              _tmpDurationMillis = _cursor.getLong(_cursorIndexOfDurationMillis);
            }
            final boolean _tmpIsNightUsage;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsNightUsage);
            _tmpIsNightUsage = _tmp != 0;
            final int _tmpStressScore;
            _tmpStressScore = _cursor.getInt(_cursorIndexOfStressScore);
            final String _tmpSpokenText;
            if (_cursor.isNull(_cursorIndexOfSpokenText)) {
              _tmpSpokenText = null;
            } else {
              _tmpSpokenText = _cursor.getString(_cursorIndexOfSpokenText);
            }
            _item = new AppUsageEvent(_tmpId,_tmpPackageName,_tmpTimestamp,_tmpDurationMillis,_tmpIsNightUsage,_tmpStressScore,_tmpSpokenText);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLateNightUsage(final long since,
      final Continuation<? super List<AppUsageEvent>> $completion) {
    final String _sql = "SELECT * FROM app_usage_events WHERE isNightUsage = 1 AND timestamp >= ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEvent>>() {
      @Override
      @NonNull
      public List<AppUsageEvent> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDurationMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMillis");
          final int _cursorIndexOfIsNightUsage = CursorUtil.getColumnIndexOrThrow(_cursor, "isNightUsage");
          final int _cursorIndexOfStressScore = CursorUtil.getColumnIndexOrThrow(_cursor, "stressScore");
          final int _cursorIndexOfSpokenText = CursorUtil.getColumnIndexOrThrow(_cursor, "spokenText");
          final List<AppUsageEvent> _result = new ArrayList<AppUsageEvent>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEvent _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDurationMillis;
            if (_cursor.isNull(_cursorIndexOfDurationMillis)) {
              _tmpDurationMillis = null;
            } else {
              _tmpDurationMillis = _cursor.getLong(_cursorIndexOfDurationMillis);
            }
            final boolean _tmpIsNightUsage;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsNightUsage);
            _tmpIsNightUsage = _tmp != 0;
            final int _tmpStressScore;
            _tmpStressScore = _cursor.getInt(_cursorIndexOfStressScore);
            final String _tmpSpokenText;
            if (_cursor.isNull(_cursorIndexOfSpokenText)) {
              _tmpSpokenText = null;
            } else {
              _tmpSpokenText = _cursor.getString(_cursorIndexOfSpokenText);
            }
            _item = new AppUsageEvent(_tmpId,_tmpPackageName,_tmpTimestamp,_tmpDurationMillis,_tmpIsNightUsage,_tmpStressScore,_tmpSpokenText);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getUsageByPackage(final String pkg, final long since,
      final Continuation<? super List<AppUsageEvent>> $completion) {
    final String _sql = "SELECT * FROM app_usage_events WHERE packageName = ? AND timestamp >= ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (pkg == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, pkg);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEvent>>() {
      @Override
      @NonNull
      public List<AppUsageEvent> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDurationMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMillis");
          final int _cursorIndexOfIsNightUsage = CursorUtil.getColumnIndexOrThrow(_cursor, "isNightUsage");
          final int _cursorIndexOfStressScore = CursorUtil.getColumnIndexOrThrow(_cursor, "stressScore");
          final int _cursorIndexOfSpokenText = CursorUtil.getColumnIndexOrThrow(_cursor, "spokenText");
          final List<AppUsageEvent> _result = new ArrayList<AppUsageEvent>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEvent _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDurationMillis;
            if (_cursor.isNull(_cursorIndexOfDurationMillis)) {
              _tmpDurationMillis = null;
            } else {
              _tmpDurationMillis = _cursor.getLong(_cursorIndexOfDurationMillis);
            }
            final boolean _tmpIsNightUsage;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsNightUsage);
            _tmpIsNightUsage = _tmp != 0;
            final int _tmpStressScore;
            _tmpStressScore = _cursor.getInt(_cursorIndexOfStressScore);
            final String _tmpSpokenText;
            if (_cursor.isNull(_cursorIndexOfSpokenText)) {
              _tmpSpokenText = null;
            } else {
              _tmpSpokenText = _cursor.getString(_cursorIndexOfSpokenText);
            }
            _item = new AppUsageEvent(_tmpId,_tmpPackageName,_tmpTimestamp,_tmpDurationMillis,_tmpIsNightUsage,_tmpStressScore,_tmpSpokenText);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getOpenCount(final String pkg, final long since,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM app_usage_events WHERE packageName = ? AND timestamp >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (pkg == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, pkg);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getMostUsedApps(final long since, final int limit,
      final Continuation<? super List<AppCount>> $completion) {
    final String _sql = "SELECT packageName, COUNT(*) as cnt FROM app_usage_events WHERE timestamp >= ? GROUP BY packageName ORDER BY cnt DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppCount>>() {
      @Override
      @NonNull
      public List<AppCount> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = 0;
          final int _cursorIndexOfCnt = 1;
          final List<AppCount> _result = new ArrayList<AppCount>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppCount _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final int _tmpCnt;
            _tmpCnt = _cursor.getInt(_cursorIndexOfCnt);
            _item = new AppCount(_tmpPackageName,_tmpCnt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getStressEvents(final int minStress, final long since,
      final Continuation<? super List<AppUsageEvent>> $completion) {
    final String _sql = "SELECT * FROM app_usage_events WHERE stressScore >= ? AND timestamp >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, minStress);
    _argIndex = 2;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEvent>>() {
      @Override
      @NonNull
      public List<AppUsageEvent> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDurationMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMillis");
          final int _cursorIndexOfIsNightUsage = CursorUtil.getColumnIndexOrThrow(_cursor, "isNightUsage");
          final int _cursorIndexOfStressScore = CursorUtil.getColumnIndexOrThrow(_cursor, "stressScore");
          final int _cursorIndexOfSpokenText = CursorUtil.getColumnIndexOrThrow(_cursor, "spokenText");
          final List<AppUsageEvent> _result = new ArrayList<AppUsageEvent>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEvent _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDurationMillis;
            if (_cursor.isNull(_cursorIndexOfDurationMillis)) {
              _tmpDurationMillis = null;
            } else {
              _tmpDurationMillis = _cursor.getLong(_cursorIndexOfDurationMillis);
            }
            final boolean _tmpIsNightUsage;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsNightUsage);
            _tmpIsNightUsage = _tmp != 0;
            final int _tmpStressScore;
            _tmpStressScore = _cursor.getInt(_cursorIndexOfStressScore);
            final String _tmpSpokenText;
            if (_cursor.isNull(_cursorIndexOfSpokenText)) {
              _tmpSpokenText = null;
            } else {
              _tmpSpokenText = _cursor.getString(_cursorIndexOfSpokenText);
            }
            _item = new AppUsageEvent(_tmpId,_tmpPackageName,_tmpTimestamp,_tmpDurationMillis,_tmpIsNightUsage,_tmpStressScore,_tmpSpokenText);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLatestEvents(final int limit,
      final Continuation<? super List<AppUsageEvent>> $completion) {
    final String _sql = "SELECT * FROM app_usage_events ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppUsageEvent>>() {
      @Override
      @NonNull
      public List<AppUsageEvent> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDurationMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMillis");
          final int _cursorIndexOfIsNightUsage = CursorUtil.getColumnIndexOrThrow(_cursor, "isNightUsage");
          final int _cursorIndexOfStressScore = CursorUtil.getColumnIndexOrThrow(_cursor, "stressScore");
          final int _cursorIndexOfSpokenText = CursorUtil.getColumnIndexOrThrow(_cursor, "spokenText");
          final List<AppUsageEvent> _result = new ArrayList<AppUsageEvent>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppUsageEvent _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDurationMillis;
            if (_cursor.isNull(_cursorIndexOfDurationMillis)) {
              _tmpDurationMillis = null;
            } else {
              _tmpDurationMillis = _cursor.getLong(_cursorIndexOfDurationMillis);
            }
            final boolean _tmpIsNightUsage;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsNightUsage);
            _tmpIsNightUsage = _tmp != 0;
            final int _tmpStressScore;
            _tmpStressScore = _cursor.getInt(_cursorIndexOfStressScore);
            final String _tmpSpokenText;
            if (_cursor.isNull(_cursorIndexOfSpokenText)) {
              _tmpSpokenText = null;
            } else {
              _tmpSpokenText = _cursor.getString(_cursorIndexOfSpokenText);
            }
            _item = new AppUsageEvent(_tmpId,_tmpPackageName,_tmpTimestamp,_tmpDurationMillis,_tmpIsNightUsage,_tmpStressScore,_tmpSpokenText);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
