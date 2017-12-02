// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProtoHead.proto

#ifndef PROTOBUF_ProtoHead_2eproto__INCLUDED
#define PROTOBUF_ProtoHead_2eproto__INCLUDED

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
#include <google/protobuf/repeated_field.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/generated_enum_reflection.h>
// @@protoc_insertion_point(includes)

namespace protocol {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_ProtoHead_2eproto();
void protobuf_InitDefaults_ProtoHead_2eproto();
void protobuf_AssignDesc_ProtoHead_2eproto();
void protobuf_ShutdownFile_ProtoHead_2eproto();


enum ENetworkMessage {
  KEEP_ALIVE_SYNC = 0,
  REGISTER_REQ = 1,
  REGISTER_RSP = 2,
  LOGIN_REQ = 3,
  LOGIN_RSP = 4,
  PERSONALSETTINGS_REQ = 5,
  PERSONALSETTINGS_RSP = 6,
  GET_USERINFO_REQ = 7,
  GET_USERINFO_RSP = 8,
  ADD_FRIEND_REQ = 9,
  ADD_FRIEND_RSP = 10,
  DELETE_FRIEND_REQ = 11,
  DELETE_FRIEND_RSP = 12,
  OFFLINE_SYNC = 13,
  LOGOUT_REQ = 14,
  LOGOUT_RSP = 15,
  GET_PERSONALINFO_REQ = 16,
  GET_PERSONALINFO_RSP = 17,
  CHANGE_FRIEND_SYNC = 18,
  SEND_CHAT_REQ = 19,
  SEND_CHAT_RSP = 20,
  RECEIVE_CHAT_SYNC = 21,
  CREATE_GROUP_CHAT_REQ = 22,
  CREATE_GROUP_CHAT_RSP = 23,
  CHANGE_GROUP_REQ = 24,
  CHANGE_GROUP_RSP = 25,
  CHANGE_GROUP_SYNC = 26,
  GET_GROUP_INFO_REQ = 27,
  GET_GROUP_INFO_RSP = 28,
  GET_FRIEND_LIST_REQ = 29,
  GET_FRIEND_LIST_RSP = 30
};
bool ENetworkMessage_IsValid(int value);
const ENetworkMessage ENetworkMessage_MIN = KEEP_ALIVE_SYNC;
const ENetworkMessage ENetworkMessage_MAX = GET_FRIEND_LIST_RSP;
const int ENetworkMessage_ARRAYSIZE = ENetworkMessage_MAX + 1;

const ::google::protobuf::EnumDescriptor* ENetworkMessage_descriptor();
inline const ::std::string& ENetworkMessage_Name(ENetworkMessage value) {
  return ::google::protobuf::internal::NameOfEnum(
    ENetworkMessage_descriptor(), value);
}
inline bool ENetworkMessage_Parse(
    const ::std::string& name, ENetworkMessage* value) {
  return ::google::protobuf::internal::ParseNamedEnum<ENetworkMessage>(
    ENetworkMessage_descriptor(), name, value);
}
// ===================================================================


// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace protocol

#ifndef SWIG
namespace google {
namespace protobuf {

template <> struct is_proto_enum< ::protocol::ENetworkMessage> : ::google::protobuf::internal::true_type {};
template <>
inline const EnumDescriptor* GetEnumDescriptor< ::protocol::ENetworkMessage>() {
  return ::protocol::ENetworkMessage_descriptor();
}

}  // namespace protobuf
}  // namespace google
#endif  // SWIG

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_ProtoHead_2eproto__INCLUDED