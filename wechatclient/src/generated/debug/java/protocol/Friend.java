// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Friend.proto

package protocol;

public final class Friend {
  private Friend() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface FriendItemOrBuilder extends
      // @@protoc_insertion_point(interface_extends:protocol.FriendItem)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string userId = 1;</code>
     */
    boolean hasUserId();
    /**
     * <code>required string userId = 1;</code>
     */
    java.lang.String getUserId();
    /**
     * <code>required string userId = 1;</code>
     */
    com.google.protobuf.ByteString
        getUserIdBytes();

    /**
     * <code>required string friendId = 2;</code>
     */
    boolean hasFriendId();
    /**
     * <code>required string friendId = 2;</code>
     */
    java.lang.String getFriendId();
    /**
     * <code>required string friendId = 2;</code>
     */
    com.google.protobuf.ByteString
        getFriendIdBytes();

    /**
     * <code>optional string friendName = 3;</code>
     */
    boolean hasFriendName();
    /**
     * <code>optional string friendName = 3;</code>
     */
    java.lang.String getFriendName();
    /**
     * <code>optional string friendName = 3;</code>
     */
    com.google.protobuf.ByteString
        getFriendNameBytes();

    /**
     * <code>optional string headPath = 4;</code>
     */
    boolean hasHeadPath();
    /**
     * <code>optional string headPath = 4;</code>
     */
    java.lang.String getHeadPath();
    /**
     * <code>optional string headPath = 4;</code>
     */
    com.google.protobuf.ByteString
        getHeadPathBytes();

    /**
     * <code>optional int64 creatTime = 5;</code>
     */
    boolean hasCreatTime();
    /**
     * <code>optional int64 creatTime = 5;</code>
     */
    long getCreatTime();
  }
  /**
   * Protobuf type {@code protocol.FriendItem}
   */
  public  static final class FriendItem extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:protocol.FriendItem)
      FriendItemOrBuilder {
    // Use FriendItem.newBuilder() to construct.
    private FriendItem(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private FriendItem() {
      userId_ = "";
      friendId_ = "";
      friendName_ = "";
      headPath_ = "";
      creatTime_ = 0L;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private FriendItem(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              userId_ = bs;
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              friendId_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              friendName_ = bs;
              break;
            }
            case 34: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000008;
              headPath_ = bs;
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              creatTime_ = input.readInt64();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protocol.Friend.internal_static_protocol_FriendItem_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protocol.Friend.internal_static_protocol_FriendItem_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              protocol.Friend.FriendItem.class, protocol.Friend.FriendItem.Builder.class);
    }

    private int bitField0_;
    public static final int USERID_FIELD_NUMBER = 1;
    private volatile java.lang.Object userId_;
    /**
     * <code>required string userId = 1;</code>
     */
    public boolean hasUserId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string userId = 1;</code>
     */
    public java.lang.String getUserId() {
      java.lang.Object ref = userId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          userId_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string userId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUserIdBytes() {
      java.lang.Object ref = userId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int FRIENDID_FIELD_NUMBER = 2;
    private volatile java.lang.Object friendId_;
    /**
     * <code>required string friendId = 2;</code>
     */
    public boolean hasFriendId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string friendId = 2;</code>
     */
    public java.lang.String getFriendId() {
      java.lang.Object ref = friendId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          friendId_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string friendId = 2;</code>
     */
    public com.google.protobuf.ByteString
        getFriendIdBytes() {
      java.lang.Object ref = friendId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        friendId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int FRIENDNAME_FIELD_NUMBER = 3;
    private volatile java.lang.Object friendName_;
    /**
     * <code>optional string friendName = 3;</code>
     */
    public boolean hasFriendName() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string friendName = 3;</code>
     */
    public java.lang.String getFriendName() {
      java.lang.Object ref = friendName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          friendName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string friendName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getFriendNameBytes() {
      java.lang.Object ref = friendName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        friendName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int HEADPATH_FIELD_NUMBER = 4;
    private volatile java.lang.Object headPath_;
    /**
     * <code>optional string headPath = 4;</code>
     */
    public boolean hasHeadPath() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string headPath = 4;</code>
     */
    public java.lang.String getHeadPath() {
      java.lang.Object ref = headPath_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          headPath_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string headPath = 4;</code>
     */
    public com.google.protobuf.ByteString
        getHeadPathBytes() {
      java.lang.Object ref = headPath_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        headPath_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int CREATTIME_FIELD_NUMBER = 5;
    private long creatTime_;
    /**
     * <code>optional int64 creatTime = 5;</code>
     */
    public boolean hasCreatTime() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int64 creatTime = 5;</code>
     */
    public long getCreatTime() {
      return creatTime_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasUserId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasFriendId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, userId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, friendId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, friendName_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, headPath_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt64(5, creatTime_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, userId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, friendId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, friendName_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, headPath_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(5, creatTime_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof protocol.Friend.FriendItem)) {
        return super.equals(obj);
      }
      protocol.Friend.FriendItem other = (protocol.Friend.FriendItem) obj;

      boolean result = true;
      result = result && (hasUserId() == other.hasUserId());
      if (hasUserId()) {
        result = result && getUserId()
            .equals(other.getUserId());
      }
      result = result && (hasFriendId() == other.hasFriendId());
      if (hasFriendId()) {
        result = result && getFriendId()
            .equals(other.getFriendId());
      }
      result = result && (hasFriendName() == other.hasFriendName());
      if (hasFriendName()) {
        result = result && getFriendName()
            .equals(other.getFriendName());
      }
      result = result && (hasHeadPath() == other.hasHeadPath());
      if (hasHeadPath()) {
        result = result && getHeadPath()
            .equals(other.getHeadPath());
      }
      result = result && (hasCreatTime() == other.hasCreatTime());
      if (hasCreatTime()) {
        result = result && (getCreatTime()
            == other.getCreatTime());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      if (hasUserId()) {
        hash = (37 * hash) + USERID_FIELD_NUMBER;
        hash = (53 * hash) + getUserId().hashCode();
      }
      if (hasFriendId()) {
        hash = (37 * hash) + FRIENDID_FIELD_NUMBER;
        hash = (53 * hash) + getFriendId().hashCode();
      }
      if (hasFriendName()) {
        hash = (37 * hash) + FRIENDNAME_FIELD_NUMBER;
        hash = (53 * hash) + getFriendName().hashCode();
      }
      if (hasHeadPath()) {
        hash = (37 * hash) + HEADPATH_FIELD_NUMBER;
        hash = (53 * hash) + getHeadPath().hashCode();
      }
      if (hasCreatTime()) {
        hash = (37 * hash) + CREATTIME_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            getCreatTime());
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static protocol.Friend.FriendItem parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protocol.Friend.FriendItem parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protocol.Friend.FriendItem parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protocol.Friend.FriendItem parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protocol.Friend.FriendItem parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protocol.Friend.FriendItem parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static protocol.Friend.FriendItem parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static protocol.Friend.FriendItem parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static protocol.Friend.FriendItem parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protocol.Friend.FriendItem parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(protocol.Friend.FriendItem prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code protocol.FriendItem}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:protocol.FriendItem)
        protocol.Friend.FriendItemOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protocol.Friend.internal_static_protocol_FriendItem_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protocol.Friend.internal_static_protocol_FriendItem_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                protocol.Friend.FriendItem.class, protocol.Friend.FriendItem.Builder.class);
      }

      // Construct using protocol.Friend.FriendItem.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        userId_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        friendId_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        friendName_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        headPath_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        creatTime_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protocol.Friend.internal_static_protocol_FriendItem_descriptor;
      }

      public protocol.Friend.FriendItem getDefaultInstanceForType() {
        return protocol.Friend.FriendItem.getDefaultInstance();
      }

      public protocol.Friend.FriendItem build() {
        protocol.Friend.FriendItem result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public protocol.Friend.FriendItem buildPartial() {
        protocol.Friend.FriendItem result = new protocol.Friend.FriendItem(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userId_ = userId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.friendId_ = friendId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.friendName_ = friendName_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.headPath_ = headPath_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.creatTime_ = creatTime_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protocol.Friend.FriendItem) {
          return mergeFrom((protocol.Friend.FriendItem)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(protocol.Friend.FriendItem other) {
        if (other == protocol.Friend.FriendItem.getDefaultInstance()) return this;
        if (other.hasUserId()) {
          bitField0_ |= 0x00000001;
          userId_ = other.userId_;
          onChanged();
        }
        if (other.hasFriendId()) {
          bitField0_ |= 0x00000002;
          friendId_ = other.friendId_;
          onChanged();
        }
        if (other.hasFriendName()) {
          bitField0_ |= 0x00000004;
          friendName_ = other.friendName_;
          onChanged();
        }
        if (other.hasHeadPath()) {
          bitField0_ |= 0x00000008;
          headPath_ = other.headPath_;
          onChanged();
        }
        if (other.hasCreatTime()) {
          setCreatTime(other.getCreatTime());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUserId()) {
          return false;
        }
        if (!hasFriendId()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        protocol.Friend.FriendItem parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (protocol.Friend.FriendItem) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object userId_ = "";
      /**
       * <code>required string userId = 1;</code>
       */
      public boolean hasUserId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string userId = 1;</code>
       */
      public java.lang.String getUserId() {
        java.lang.Object ref = userId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            userId_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string userId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getUserIdBytes() {
        java.lang.Object ref = userId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          userId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string userId = 1;</code>
       */
      public Builder setUserId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        userId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string userId = 1;</code>
       */
      public Builder clearUserId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userId_ = getDefaultInstance().getUserId();
        onChanged();
        return this;
      }
      /**
       * <code>required string userId = 1;</code>
       */
      public Builder setUserIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        userId_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object friendId_ = "";
      /**
       * <code>required string friendId = 2;</code>
       */
      public boolean hasFriendId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string friendId = 2;</code>
       */
      public java.lang.String getFriendId() {
        java.lang.Object ref = friendId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            friendId_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string friendId = 2;</code>
       */
      public com.google.protobuf.ByteString
          getFriendIdBytes() {
        java.lang.Object ref = friendId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          friendId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string friendId = 2;</code>
       */
      public Builder setFriendId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        friendId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string friendId = 2;</code>
       */
      public Builder clearFriendId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        friendId_ = getDefaultInstance().getFriendId();
        onChanged();
        return this;
      }
      /**
       * <code>required string friendId = 2;</code>
       */
      public Builder setFriendIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        friendId_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object friendName_ = "";
      /**
       * <code>optional string friendName = 3;</code>
       */
      public boolean hasFriendName() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string friendName = 3;</code>
       */
      public java.lang.String getFriendName() {
        java.lang.Object ref = friendName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            friendName_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string friendName = 3;</code>
       */
      public com.google.protobuf.ByteString
          getFriendNameBytes() {
        java.lang.Object ref = friendName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          friendName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string friendName = 3;</code>
       */
      public Builder setFriendName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        friendName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string friendName = 3;</code>
       */
      public Builder clearFriendName() {
        bitField0_ = (bitField0_ & ~0x00000004);
        friendName_ = getDefaultInstance().getFriendName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string friendName = 3;</code>
       */
      public Builder setFriendNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        friendName_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object headPath_ = "";
      /**
       * <code>optional string headPath = 4;</code>
       */
      public boolean hasHeadPath() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional string headPath = 4;</code>
       */
      public java.lang.String getHeadPath() {
        java.lang.Object ref = headPath_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            headPath_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string headPath = 4;</code>
       */
      public com.google.protobuf.ByteString
          getHeadPathBytes() {
        java.lang.Object ref = headPath_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          headPath_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string headPath = 4;</code>
       */
      public Builder setHeadPath(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        headPath_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string headPath = 4;</code>
       */
      public Builder clearHeadPath() {
        bitField0_ = (bitField0_ & ~0x00000008);
        headPath_ = getDefaultInstance().getHeadPath();
        onChanged();
        return this;
      }
      /**
       * <code>optional string headPath = 4;</code>
       */
      public Builder setHeadPathBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        headPath_ = value;
        onChanged();
        return this;
      }

      private long creatTime_ ;
      /**
       * <code>optional int64 creatTime = 5;</code>
       */
      public boolean hasCreatTime() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional int64 creatTime = 5;</code>
       */
      public long getCreatTime() {
        return creatTime_;
      }
      /**
       * <code>optional int64 creatTime = 5;</code>
       */
      public Builder setCreatTime(long value) {
        bitField0_ |= 0x00000010;
        creatTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 creatTime = 5;</code>
       */
      public Builder clearCreatTime() {
        bitField0_ = (bitField0_ & ~0x00000010);
        creatTime_ = 0L;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:protocol.FriendItem)
    }

    // @@protoc_insertion_point(class_scope:protocol.FriendItem)
    private static final protocol.Friend.FriendItem DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new protocol.Friend.FriendItem();
    }

    public static protocol.Friend.FriendItem getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<FriendItem>
        PARSER = new com.google.protobuf.AbstractParser<FriendItem>() {
      public FriendItem parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new FriendItem(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<FriendItem> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<FriendItem> getParserForType() {
      return PARSER;
    }

    public protocol.Friend.FriendItem getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protocol_FriendItem_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protocol_FriendItem_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Friend.proto\022\010protocol\"g\n\nFriendItem\022\016" +
      "\n\006userId\030\001 \002(\t\022\020\n\010friendId\030\002 \002(\t\022\022\n\nfrie" +
      "ndName\030\003 \001(\t\022\020\n\010headPath\030\004 \001(\t\022\021\n\tcreatT" +
      "ime\030\005 \001(\003"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_protocol_FriendItem_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_protocol_FriendItem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protocol_FriendItem_descriptor,
        new java.lang.String[] { "UserId", "FriendId", "FriendName", "HeadPath", "CreatTime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
