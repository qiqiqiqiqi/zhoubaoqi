package com.qi.wechatclient.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.qi.wechatclient.chat.enity.MessageInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MESSAGE_INFO".
*/
public class MessageInfoDao extends AbstractDao<MessageInfo, Long> {

    public static final String TABLENAME = "MESSAGE_INFO";

    /**
     * Properties of entity MessageInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserID = new Property(1, String.class, "userID", false, "USER_ID");
        public final static Property FriendID = new Property(2, String.class, "friendID", false, "FRIEND_ID");
        public final static Property Type = new Property(3, int.class, "type", false, "TYPE");
        public final static Property Content = new Property(4, String.class, "content", false, "CONTENT");
        public final static Property Filepath = new Property(5, String.class, "filepath", false, "FILEPATH");
        public final static Property SendState = new Property(6, int.class, "sendState", false, "SEND_STATE");
        public final static Property Time = new Property(7, String.class, "time", false, "TIME");
        public final static Property Header = new Property(8, String.class, "header", false, "HEADER");
        public final static Property ImageUrl = new Property(9, String.class, "imageUrl", false, "IMAGE_URL");
        public final static Property VoiceTime = new Property(10, long.class, "voiceTime", false, "VOICE_TIME");
        public final static Property CreateTime = new Property(11, String.class, "createTime", false, "CREATE_TIME");
        public final static Property MessageInfoID = new Property(12, String.class, "messageInfoID", false, "MESSAGE_INFO_ID");
    }


    public MessageInfoDao(DaoConfig config) {
        super(config);
    }
    
    public MessageInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MESSAGE_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USER_ID\" TEXT," + // 1: userID
                "\"FRIEND_ID\" TEXT," + // 2: friendID
                "\"TYPE\" INTEGER NOT NULL ," + // 3: type
                "\"CONTENT\" TEXT," + // 4: content
                "\"FILEPATH\" TEXT," + // 5: filepath
                "\"SEND_STATE\" INTEGER NOT NULL ," + // 6: sendState
                "\"TIME\" TEXT," + // 7: time
                "\"HEADER\" TEXT," + // 8: header
                "\"IMAGE_URL\" TEXT," + // 9: imageUrl
                "\"VOICE_TIME\" INTEGER NOT NULL ," + // 10: voiceTime
                "\"CREATE_TIME\" TEXT," + // 11: createTime
                "\"MESSAGE_INFO_ID\" TEXT UNIQUE );"); // 12: messageInfoID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MESSAGE_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MessageInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userID = entity.getUserID();
        if (userID != null) {
            stmt.bindString(2, userID);
        }
 
        String friendID = entity.getFriendID();
        if (friendID != null) {
            stmt.bindString(3, friendID);
        }
        stmt.bindLong(4, entity.getType());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        String filepath = entity.getFilepath();
        if (filepath != null) {
            stmt.bindString(6, filepath);
        }
        stmt.bindLong(7, entity.getSendState());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(8, time);
        }
 
        String header = entity.getHeader();
        if (header != null) {
            stmt.bindString(9, header);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(10, imageUrl);
        }
        stmt.bindLong(11, entity.getVoiceTime());
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(12, createTime);
        }
 
        String messageInfoID = entity.getMessageInfoID();
        if (messageInfoID != null) {
            stmt.bindString(13, messageInfoID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MessageInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userID = entity.getUserID();
        if (userID != null) {
            stmt.bindString(2, userID);
        }
 
        String friendID = entity.getFriendID();
        if (friendID != null) {
            stmt.bindString(3, friendID);
        }
        stmt.bindLong(4, entity.getType());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        String filepath = entity.getFilepath();
        if (filepath != null) {
            stmt.bindString(6, filepath);
        }
        stmt.bindLong(7, entity.getSendState());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(8, time);
        }
 
        String header = entity.getHeader();
        if (header != null) {
            stmt.bindString(9, header);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(10, imageUrl);
        }
        stmt.bindLong(11, entity.getVoiceTime());
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(12, createTime);
        }
 
        String messageInfoID = entity.getMessageInfoID();
        if (messageInfoID != null) {
            stmt.bindString(13, messageInfoID);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset) ? null : cursor.getLong(offset);
    }    

    @Override
    public MessageInfo readEntity(Cursor cursor, int offset) {
        MessageInfo entity = new MessageInfo( //
            cursor.isNull(offset) ? null : cursor.getLong(offset), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // friendID
            cursor.getInt(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // content
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // filepath
            cursor.getInt(offset + 6), // sendState
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // time
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // header
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // imageUrl
            cursor.getLong(offset + 10), // voiceTime
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // createTime
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // messageInfoID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MessageInfo entity, int offset) {
        entity.setId(cursor.isNull(offset) ? null : cursor.getLong(offset));
        entity.setUserID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFriendID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.getInt(offset + 3));
        entity.setContent(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFilepath(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSendState(cursor.getInt(offset + 6));
        entity.setTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setHeader(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setImageUrl(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setVoiceTime(cursor.getLong(offset + 10));
        entity.setCreateTime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setMessageInfoID(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MessageInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MessageInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MessageInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
