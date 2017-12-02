// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Result.proto

#ifndef PROTOBUF_Result_2eproto__INCLUDED
#define PROTOBUF_Result_2eproto__INCLUDED

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
void protobuf_AddDesc_Result_2eproto();
void protobuf_InitDefaults_Result_2eproto();
void protobuf_AssignDesc_Result_2eproto();
void protobuf_ShutdownFile_Result_2eproto();


enum ResultCode {
  SUCCESS = 0,
  FAIL = 1,
  USER_EXIST = 2
};
bool ResultCode_IsValid(int value);
const ResultCode ResultCode_MIN = SUCCESS;
const ResultCode ResultCode_MAX = USER_EXIST;
const int ResultCode_ARRAYSIZE = ResultCode_MAX + 1;

const ::google::protobuf::EnumDescriptor* ResultCode_descriptor();
inline const ::std::string& ResultCode_Name(ResultCode value) {
  return ::google::protobuf::internal::NameOfEnum(
    ResultCode_descriptor(), value);
}
inline bool ResultCode_Parse(
    const ::std::string& name, ResultCode* value) {
  return ::google::protobuf::internal::ParseNamedEnum<ResultCode>(
    ResultCode_descriptor(), name, value);
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

template <> struct is_proto_enum< ::protocol::ResultCode> : ::google::protobuf::internal::true_type {};
template <>
inline const EnumDescriptor* GetEnumDescriptor< ::protocol::ResultCode>() {
  return ::protocol::ResultCode_descriptor();
}

}  // namespace protobuf
}  // namespace google
#endif  // SWIG

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_Result_2eproto__INCLUDED
