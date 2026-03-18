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
public final class ConversationDao_Impl implements ConversationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ConversationMemory> __insertionAdapterOfConversationMemory;

  private final SharedSQLiteStatement __preparedStmtOfClearHistory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldConversations;

  public ConversationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfConversationMemory = new EntityInsertionAdapter<ConversationMemory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `conversation_memory` (`id`,`timestamp`,`userSaid`,`akvaReplied`,`context`,`actionTaken`) VALUES (nullif(?, 0),?,?,?,?,?)";
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
        if (entity.getContext() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getContext());
        }
        if (entity.getActionTaken() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getActionTaken());
        }
      }
    };
    this.__preparedStmtOfClearHistory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM conversation_memory";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldConversations = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM conversation_memory WHERE timestamp < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ConversationMemory conversation,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfConversationMemory.insert(conversation);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearHistory(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearHistory.acquire();
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
          __preparedStmtOfClearHistory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldConversations(final long threshold,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldConversations.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, threshold);
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
          __preparedStmtOfDeleteOldConversations.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getRecentConversations(final int limit,
      final Continuation<? super List<ConversationMemory>> $completion) {
    final String _sql = "SELECT * FROM conversation_memory ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ConversationMemory>>() {
      @Override
      @NonNull
      public List<ConversationMemory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserSaid = CursorUtil.getColumnIndexOrThrow(_cursor, "userSaid");
          final int _cursorIndexOfAkvaReplied = CursorUtil.getColumnIndexOrThrow(_cursor, "akvaReplied");
          final int _cursorIndexOfContext = CursorUtil.getColumnIndexOrThrow(_cursor, "context");
          final int _cursorIndexOfActionTaken = CursorUtil.getColumnIndexOrThrow(_cursor, "actionTaken");
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
            final String _tmpContext;
            if (_cursor.isNull(_cursorIndexOfContext)) {
              _tmpContext = null;
            } else {
              _tmpContext = _cursor.getString(_cursorIndexOfContext);
            }
            final String _tmpActionTaken;
            if (_cursor.isNull(_cursorIndexOfActionTaken)) {
              _tmpActionTaken = null;
            } else {
              _tmpActionTaken = _cursor.getString(_cursorIndexOfActionTaken);
            }
            _item = new ConversationMemory(_tmpId,_tmpTimestamp,_tmpUserSaid,_tmpAkvaReplied,_tmpContext,_tmpActionTaken);
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
