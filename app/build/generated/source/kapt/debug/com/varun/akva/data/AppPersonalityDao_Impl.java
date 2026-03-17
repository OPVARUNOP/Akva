package com.varun.akva.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppPersonalityDao_Impl implements AppPersonalityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppPersonality> __insertionAdapterOfAppPersonality;

  public AppPersonalityDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppPersonality = new EntityInsertionAdapter<AppPersonality>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `app_personalities` (`packageName`,`voiceStyle`,`isEnabled`,`pitch`,`speechRate`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppPersonality entity) {
        if (entity.getPackageName() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getPackageName());
        }
        if (entity.getVoiceStyle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVoiceStyle());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindDouble(4, entity.getPitch());
        statement.bindDouble(5, entity.getSpeechRate());
      }
    };
  }

  @Override
  public Object insertOrUpdate(final AppPersonality personality,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppPersonality.insert(personality);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object getPersonality(final String packageName,
      final Continuation<? super AppPersonality> arg1) {
    final String _sql = "SELECT * FROM app_personalities WHERE packageName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packageName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AppPersonality>() {
      @Override
      @Nullable
      public AppPersonality call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfVoiceStyle = CursorUtil.getColumnIndexOrThrow(_cursor, "voiceStyle");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfPitch = CursorUtil.getColumnIndexOrThrow(_cursor, "pitch");
          final int _cursorIndexOfSpeechRate = CursorUtil.getColumnIndexOrThrow(_cursor, "speechRate");
          final AppPersonality _result;
          if (_cursor.moveToFirst()) {
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpVoiceStyle;
            if (_cursor.isNull(_cursorIndexOfVoiceStyle)) {
              _tmpVoiceStyle = null;
            } else {
              _tmpVoiceStyle = _cursor.getString(_cursorIndexOfVoiceStyle);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final float _tmpPitch;
            _tmpPitch = _cursor.getFloat(_cursorIndexOfPitch);
            final float _tmpSpeechRate;
            _tmpSpeechRate = _cursor.getFloat(_cursorIndexOfSpeechRate);
            _result = new AppPersonality(_tmpPackageName,_tmpVoiceStyle,_tmpIsEnabled,_tmpPitch,_tmpSpeechRate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<AppPersonality>> getAllPersonalities() {
    final String _sql = "SELECT * FROM app_personalities";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_personalities"}, new Callable<List<AppPersonality>>() {
      @Override
      @NonNull
      public List<AppPersonality> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfVoiceStyle = CursorUtil.getColumnIndexOrThrow(_cursor, "voiceStyle");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfPitch = CursorUtil.getColumnIndexOrThrow(_cursor, "pitch");
          final int _cursorIndexOfSpeechRate = CursorUtil.getColumnIndexOrThrow(_cursor, "speechRate");
          final List<AppPersonality> _result = new ArrayList<AppPersonality>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppPersonality _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpVoiceStyle;
            if (_cursor.isNull(_cursorIndexOfVoiceStyle)) {
              _tmpVoiceStyle = null;
            } else {
              _tmpVoiceStyle = _cursor.getString(_cursorIndexOfVoiceStyle);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final float _tmpPitch;
            _tmpPitch = _cursor.getFloat(_cursorIndexOfPitch);
            final float _tmpSpeechRate;
            _tmpSpeechRate = _cursor.getFloat(_cursorIndexOfSpeechRate);
            _item = new AppPersonality(_tmpPackageName,_tmpVoiceStyle,_tmpIsEnabled,_tmpPitch,_tmpSpeechRate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
