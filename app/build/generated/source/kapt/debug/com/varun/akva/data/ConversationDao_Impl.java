package com.varun.akva.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
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
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ConversationDao_Impl implements ConversationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ConversationMemory> __insertionAdapterOfConversationMemory;

  public ConversationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfConversationMemory = new EntityInsertionAdapter<ConversationMemory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `conversation` (`id`,`timestamp`,`userSaid`,`akvaReplied`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ConversationMemory entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTimestamp());
        if (entity.getUserSaid() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getUserSaid());
        }
        if (entity.getAkvaReplied() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAkvaReplied());
        }
      }
    };
  }

  @Override
  public Object insert(final ConversationMemory m, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfConversationMemory.insert(m);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ConversationMemory>> getHistory(final int limit) {
    final String _sql = "SELECT * FROM conversation ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"conversation"}, new Callable<List<ConversationMemory>>() {
      @Override
      @NonNull
      public List<ConversationMemory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserSaid = CursorUtil.getColumnIndexOrThrow(_cursor, "userSaid");
          final int _cursorIndexOfAkvaReplied = CursorUtil.getColumnIndexOrThrow(_cursor, "akvaReplied");
          final List<ConversationMemory> _result = new ArrayList<ConversationMemory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ConversationMemory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserSaid;
            if (_cursor.isNull(_cursorIndexOfUserSaid)) {
              _tmpUserSaid = null;
            } else {
              _tmpUserSaid = _cursor.getString(_cursorIndexOfUserSaid);
            }
            final String _tmpAkvaReplied;
            if (_cursor.isNull(_cursorIndexOfAkvaReplied)) {
              _tmpAkvaReplied = null;
            } else {
              _tmpAkvaReplied = _cursor.getString(_cursorIndexOfAkvaReplied);
            }
            _item = new ConversationMemory(_tmpId,_tmpTimestamp,_tmpUserSaid,_tmpAkvaReplied);
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
