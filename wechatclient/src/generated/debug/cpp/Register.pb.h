// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Register.proto

#ifndef PROTOBUF_Register_2eproto__INCLUDED
#define PROTOBUF_Register_2eproto__INCLUDED

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
#include "Result.pb.h"
#include "UserData.pb.h"
// @@protoc_insertion_point(includes)

namespace protocol {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_Register_2eproto();
void protobuf_InitDefaults_Register_2eproto();
void protobuf_AssignDesc_Register_2eproto();
void protobuf_ShutdownFile_Register_2eproto();

class RegisterReq;
class RegisterRsp;

// ===================================================================

class RegisterReq : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:protocol.RegisterReq) */ {
 public:
  RegisterReq();
  virtual ~RegisterReq();

  RegisterReq(const RegisterReq& from);

  inline RegisterReq& operator=(const RegisterReq& from) {
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
  static const RegisterReq& default_instance();

  static const RegisterReq* internal_default_instance();

  void Swap(RegisterReq* other);

  // implements Message ----------------------------------------------

  inline RegisterReq* New() const { return New(NULL); }

  RegisterReq* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const RegisterReq& from);
  void MergeFrom(const RegisterReq& from);
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
  void InternalSwap(RegisterReq* other);
  void UnsafeMergeFrom(const RegisterReq& from);
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

  // required string userName = 1;
  bool has_username() const;
  void clear_username();
  static const int kUserNameFieldNumber = 1;
  const ::std::string& username() const;
  void set_username(const ::std::string& value);
  void set_username(const char* value);
  void set_username(const char* value, size_t size);
  ::std::string* mutable_username();
  ::std::string* release_username();
  void set_allocated_username(::std::string* username);

  // required string userPassword = 2;
  bool has_userpassword() const;
  void clear_userpassword();
  static const int kUserPasswordFieldNumber = 2;
  const ::std::string& userpassword() const;
  void set_userpassword(const ::std::string& value);
  void set_userpassword(const char* value);
  void set_userpassword(const char* value, size_t size);
  ::std::string* mutable_userpassword();
  ::std::string* release_userpassword();
  void set_allocated_userpassword(::std::string* userpassword);

  // @@protoc_insertion_point(class_scope:protocol.RegisterReq)
 private:
  inline void set_has_username();
  inline void clear_has_username();
  inline void set_has_userpassword();
  inline void clear_has_userpassword();

  // helper for ByteSizeLong()
  size_t RequiredFieldsByteSizeFallback() const;

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::HasBits<1> _has_bits_;
  mutable int _cached_size_;
  ::google::protobuf::internal::ArenaStringPtr username_;
  ::google::protobuf::internal::ArenaStringPtr userpassword_;
  friend void  protobuf_InitDefaults_Register_2eproto_impl();
  friend void  protobuf_AddDesc_Register_2eproto_impl();
  friend void protobuf_AssignDesc_Register_2eproto();
  friend void protobuf_ShutdownFile_Register_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<RegisterReq> RegisterReq_default_instance_;

// -------------------------------------------------------------------

class RegisterRsp : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:protocol.RegisterRsp) */ {
 public:
  RegisterRsp();
  virtual ~RegisterRsp();

  RegisterRsp(const RegisterRsp& from);

  inline RegisterRsp& operator=(const RegisterRsp& from) {
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
  static const RegisterRsp& default_instance();

  static const RegisterRsp* internal_default_instance();

  void Swap(RegisterRsp* other);

  // implements Message ----------------------------------------------

  inline RegisterRsp* New() const { return New(NULL); }

  RegisterRsp* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const RegisterRsp& from);
  void MergeFrom(const RegisterRsp& from);
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
  void InternalSwap(RegisterRsp* other);
  void UnsafeMergeFrom(const RegisterRsp& from);
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

  // optional .protocol.UserItem userItem = 2;
  bool has_useritem() const;
  void clear_useritem();
  static const int kUserItemFieldNumber = 2;
  const ::protocol::UserItem& useritem() const;
  ::protocol::UserItem* mutable_useritem();
  ::protocol::UserItem* release_useritem();
  void set_allocated_useritem(::protocol::UserItem* useritem);

  // required .protocol.ResultCode resultCode = 1;
  bool has_resultcode() const;
  void clear_resultcode();
  static const int kResultCodeFieldNumber = 1;
  ::protocol::ResultCode resultcode() const;
  void set_resultcode(::protocol::ResultCode value);

  // @@protoc_insertion_point(class_scope:protocol.RegisterRsp)
 private:
  inline void set_has_useritem();
  inline void clear_has_useritem();
  inline void set_has_resultcode();
  inline void clear_has_resultcode();

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::HasBits<1> _has_bits_;
  mutable int _cached_size_;
  ::protocol::UserItem* useritem_;
  int resultcode_;
  friend void  protobuf_InitDefaults_Register_2eproto_impl();
  friend void  protobuf_AddDesc_Register_2eproto_impl();
  friend void protobuf_AssignDesc_Register_2eproto();
  friend void protobuf_ShutdownFile_Register_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<RegisterRsp> RegisterRsp_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// RegisterReq

// required string userName = 1;
inline bool RegisterReq::has_username() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void RegisterReq::set_has_username() {
  _has_bits_[0] |= 0x00000001u;
}
inline void RegisterReq::clear_has_username() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void RegisterReq::clear_username() {
  username_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_username();
}
inline const ::std::string& RegisterReq::username() const {
  // @@protoc_insertion_point(field_get:protocol.RegisterReq.userName)
  return username_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void RegisterReq::set_username(const ::std::string& value) {
  set_has_username();
  username_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:protocol.RegisterReq.userName)
}
inline void RegisterReq::set_username(const char* value) {
  set_has_username();
  username_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:protocol.RegisterReq.userName)
}
inline void RegisterReq::set_username(const char* value, size_t size) {
  set_has_username();
  username_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:protocol.RegisterReq.userName)
}
inline ::std::string* RegisterReq::mutable_username() {
  set_has_username();
  // @@protoc_insertion_point(field_mutable:protocol.RegisterReq.userName)
  return username_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* RegisterReq::release_username() {
  // @@protoc_insertion_point(field_release:protocol.RegisterReq.userName)
  clear_has_username();
  return username_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void RegisterReq::set_allocated_username(::std::string* username) {
  if (username != NULL) {
    set_has_username();
  } else {
    clear_has_username();
  }
  username_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), username);
  // @@protoc_insertion_point(field_set_allocated:protocol.RegisterReq.userName)
}

// required string userPassword = 2;
inline bool RegisterReq::has_userpassword() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
inline void RegisterReq::set_has_userpassword() {
  _has_bits_[0] |= 0x00000002u;
}
inline void RegisterReq::clear_has_userpassword() {
  _has_bits_[0] &= ~0x00000002u;
}
inline void RegisterReq::clear_userpassword() {
  userpassword_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_userpassword();
}
inline const ::std::string& RegisterReq::userpassword() const {
  // @@protoc_insertion_point(field_get:protocol.RegisterReq.userPassword)
  return userpassword_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void RegisterReq::set_userpassword(const ::std::string& value) {
  set_has_userpassword();
  userpassword_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:protocol.RegisterReq.userPassword)
}
inline void RegisterReq::set_userpassword(const char* value) {
  set_has_userpassword();
  userpassword_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:protocol.RegisterReq.userPassword)
}
inline void RegisterReq::set_userpassword(const char* value, size_t size) {
  set_has_userpassword();
  userpassword_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:protocol.RegisterReq.userPassword)
}
inline ::std::string* RegisterReq::mutable_userpassword() {
  set_has_userpassword();
  // @@protoc_insertion_point(field_mutable:protocol.RegisterReq.userPassword)
  return userpassword_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* RegisterReq::release_userpassword() {
  // @@protoc_insertion_point(field_release:protocol.RegisterReq.userPassword)
  clear_has_userpassword();
  return userpassword_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void RegisterReq::set_allocated_userpassword(::std::string* userpassword) {
  if (userpassword != NULL) {
    set_has_userpassword();
  } else {
    clear_has_userpassword();
  }
  userpassword_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userpassword);
  // @@protoc_insertion_point(field_set_allocated:protocol.RegisterReq.userPassword)
}

inline const RegisterReq* RegisterReq::internal_default_instance() {
  return &RegisterReq_default_instance_.get();
}
// -------------------------------------------------------------------

// RegisterRsp

// optional .protocol.UserItem userItem = 2;
inline bool RegisterRsp::has_useritem() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void RegisterRsp::set_has_useritem() {
  _has_bits_[0] |= 0x00000001u;
}
inline void RegisterRsp::clear_has_useritem() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void RegisterRsp::clear_useritem() {
  if (useritem_ != NULL) useritem_->::protocol::UserItem::Clear();
  clear_has_useritem();
}
inline const ::protocol::UserItem& RegisterRsp::useritem() const {
  // @@protoc_insertion_point(field_get:protocol.RegisterRsp.userItem)
  return useritem_ != NULL ? *useritem_
                         : *::protocol::UserItem::internal_default_instance();
}
inline ::protocol::UserItem* RegisterRsp::mutable_useritem() {
  set_has_useritem();
  if (useritem_ == NULL) {
    useritem_ = new ::protocol::UserItem;
  }
  // @@protoc_insertion_point(field_mutable:protocol.RegisterRsp.userItem)
  return useritem_;
}
inline ::protocol::UserItem* RegisterRsp::release_useritem() {
  // @@protoc_insertion_point(field_release:protocol.RegisterRsp.userItem)
  clear_has_useritem();
  ::protocol::UserItem* temp = useritem_;
  useritem_ = NULL;
  return temp;
}
inline void RegisterRsp::set_allocated_useritem(::protocol::UserItem* useritem) {
  delete useritem_;
  useritem_ = useritem;
  if (useritem) {
    set_has_useritem();
  } else {
    clear_has_useritem();
  }
  // @@protoc_insertion_point(field_set_allocated:protocol.RegisterRsp.userItem)
}

// required .protocol.ResultCode resultCode = 1;
inline bool RegisterRsp::has_resultcode() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
inline void RegisterRsp::set_has_resultcode() {
  _has_bits_[0] |= 0x00000002u;
}
inline void RegisterRsp::clear_has_resultcode() {
  _has_bits_[0] &= ~0x00000002u;
}
inline void RegisterRsp::clear_resultcode() {
  resultcode_ = 0;
  clear_has_resultcode();
}
inline ::protocol::ResultCode RegisterRsp::resultcode() const {
  // @@protoc_insertion_point(field_get:protocol.RegisterRsp.resultCode)
  return static_cast< ::protocol::ResultCode >(resultcode_);
}
inline void RegisterRsp::set_resultcode(::protocol::ResultCode value) {
  assert(::protocol::ResultCode_IsValid(value));
  set_has_resultcode();
  resultcode_ = value;
  // @@protoc_insertion_point(field_set:protocol.RegisterRsp.resultCode)
}

inline const RegisterRsp* RegisterRsp::internal_default_instance() {
  return &RegisterRsp_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace protocol

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_Register_2eproto__INCLUDED