// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ReceiveChatMsg.proto

#ifndef PROTOBUF_ReceiveChatMsg_2eproto__INCLUDED
#define PROTOBUF_ReceiveChatMsg_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 3001000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 3001000 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/metadata.h>
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/unknown_field_set.h>
#include "ChatData.pb.h"
// @@protoc_insertion_point(includes)

namespace protocol {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_ReceiveChatMsg_2eproto();
void protobuf_InitDefaults_ReceiveChatMsg_2eproto();
void protobuf_AssignDesc_ReceiveChatMsg_2eproto();
void protobuf_ShutdownFile_ReceiveChatMsg_2eproto();

class ReceiveChatSync;

// ===================================================================

class ReceiveChatSync : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:protocol.ReceiveChatSync) */ {
 public:
  ReceiveChatSync();
  virtual ~ReceiveChatSync();

  ReceiveChatSync(const ReceiveChatSync& from);

  inline ReceiveChatSync& operator=(const ReceiveChatSync& from) {
    CopyFrom(from);
    return *this;
  }

  inline const ::google::protobuf::UnknownFieldSet& unknown_fields() const {
    return _internal_metadata_.unknown_fields();
  }

  inline ::google::protobuf::UnknownFieldSet* mutable_unknown_fields() {
    return _internal_metadata_.mutable_unknown_fields();
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const ReceiveChatSync& default_instance();

  static const ReceiveChatSync* internal_default_instance();

  void Swap(ReceiveChatSync* other);

  // implements Message ----------------------------------------------

  inline ReceiveChatSync* New() const { return New(NULL); }

  ReceiveChatSync* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const ReceiveChatSync& from);
  void MergeFrom(const ReceiveChatSync& from);
  void Clear();
  bool IsInitialized() const;

  size_t ByteSizeLong() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const {
    return InternalSerializeWithCachedSizesToArray(false, output);
  }
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(ReceiveChatSync* other);
  void UnsafeMergeFrom(const ReceiveChatSync& from);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return _internal_metadata_.arena();
  }
  inline void* MaybeArenaPtr() const {
    return _internal_metadata_.raw_arena_ptr();
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // repeated .protocol.ChatItem chatData = 1;
  int chatdata_size() const;
  void clear_chatdata();
  static const int kChatDataFieldNumber = 1;
  const ::protocol::ChatItem& chatdata(int index) const;
  ::protocol::ChatItem* mutable_chatdata(int index);
  ::protocol::ChatItem* add_chatdata();
  ::google::protobuf::RepeatedPtrField< ::protocol::ChatItem >*
      mutable_chatdata();
  const ::google::protobuf::RepeatedPtrField< ::protocol::ChatItem >&
      chatdata() const;

  // @@protoc_insertion_point(class_scope:protocol.ReceiveChatSync)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::HasBits<1> _has_bits_;
  mutable int _cached_size_;
  ::google::protobuf::RepeatedPtrField< ::protocol::ChatItem > chatdata_;
  friend void  protobuf_InitDefaults_ReceiveChatMsg_2eproto_impl();
  friend void  protobuf_AddDesc_ReceiveChatMsg_2eproto_impl();
  friend void protobuf_AssignDesc_ReceiveChatMsg_2eproto();
  friend void protobuf_ShutdownFile_ReceiveChatMsg_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<ReceiveChatSync> ReceiveChatSync_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// ReceiveChatSync

// repeated .protocol.ChatItem chatData = 1;
inline int ReceiveChatSync::chatdata_size() const {
  return chatdata_.size();
}
inline void ReceiveChatSync::clear_chatdata() {
  chatdata_.Clear();
}
inline const ::protocol::ChatItem& ReceiveChatSync::chatdata(int index) const {
  // @@protoc_insertion_point(field_get:protocol.ReceiveChatSync.chatData)
  return chatdata_.Get(index);
}
inline ::protocol::ChatItem* ReceiveChatSync::mutable_chatdata(int index) {
  // @@protoc_insertion_point(field_mutable:protocol.ReceiveChatSync.chatData)
  return chatdata_.Mutable(index);
}
inline ::protocol::ChatItem* ReceiveChatSync::add_chatdata() {
  // @@protoc_insertion_point(field_add:protocol.ReceiveChatSync.chatData)
  return chatdata_.Add();
}
inline ::google::protobuf::RepeatedPtrField< ::protocol::ChatItem >*
ReceiveChatSync::mutable_chatdata() {
  // @@protoc_insertion_point(field_mutable_list:protocol.ReceiveChatSync.chatData)
  return &chatdata_;
}
inline const ::google::protobuf::RepeatedPtrField< ::protocol::ChatItem >&
ReceiveChatSync::chatdata() const {
  // @@protoc_insertion_point(field_list:protocol.ReceiveChatSync.chatData)
  return chatdata_;
}

inline const ReceiveChatSync* ReceiveChatSync::internal_default_instance() {
  return &ReceiveChatSync_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace protocol

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_ReceiveChatMsg_2eproto__INCLUDED
