// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Result.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "Result.pb.h"

#include <algorithm>

#include <google/protobuf/stubs/common.h>
#include <google/protobuf/stubs/port.h>
#include <google/protobuf/stubs/once.h>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/wire_format_lite_inl.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/generated_message_reflection.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// @@protoc_insertion_point(includes)

namespace protocol {

namespace {

const ::google::protobuf::EnumDescriptor* ResultCode_descriptor_ = NULL;

}  // namespace


void protobuf_AssignDesc_Result_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_Result_2eproto() {
  protobuf_AddDesc_Result_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "Result.proto");
  GOOGLE_CHECK(file != NULL);
  ResultCode_descriptor_ = file->enum_type(0);
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_Result_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
}

}  // namespace

void protobuf_ShutdownFile_Result_2eproto() {
}

void protobuf_InitDefaults_Result_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_Result_2eproto_once_);
void protobuf_InitDefaults_Result_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_Result_2eproto_once_,
                 &protobuf_InitDefaults_Result_2eproto_impl);
}
void protobuf_AddDesc_Result_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_Result_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\014Result.proto\022\010protocol*3\n\nResultCode\022\013"
    "\n\007SUCCESS\020\000\022\010\n\004FAIL\020\001\022\016\n\nUSER_EXIST\020\002", 77);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "Result.proto", &protobuf_RegisterTypes);
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_Result_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_Result_2eproto_once_);
void protobuf_AddDesc_Result_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_Result_2eproto_once_,
                 &protobuf_AddDesc_Result_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_Result_2eproto {
  StaticDescriptorInitializer_Result_2eproto() {
    protobuf_AddDesc_Result_2eproto();
  }
} static_descriptor_initializer_Result_2eproto_;
const ::google::protobuf::EnumDescriptor* ResultCode_descriptor() {
  protobuf_AssignDescriptorsOnce();
  return ResultCode_descriptor_;
}
bool ResultCode_IsValid(int value) {
  switch (value) {
    case 0:
    case 1:
    case 2:
      return true;
    default:
      return false;
  }
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace protocol

// @@protoc_insertion_point(global_scope)
