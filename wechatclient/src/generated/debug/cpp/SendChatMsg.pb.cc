// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SendChatMsg.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "SendChatMsg.pb.h"

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

const ::google::protobuf::Descriptor* SendChatReq_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  SendChatReq_reflection_ = NULL;
const ::google::protobuf::Descriptor* SendChatRsp_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  SendChatRsp_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_SendChatMsg_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_SendChatMsg_2eproto() {
  protobuf_AddDesc_SendChatMsg_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "SendChatMsg.proto");
  GOOGLE_CHECK(file != NULL);
  SendChatReq_descriptor_ = file->message_type(0);
  static const int SendChatReq_offsets_[1] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(SendChatReq, chatdata_),
  };
  SendChatReq_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      SendChatReq_descriptor_,
      SendChatReq::internal_default_instance(),
      SendChatReq_offsets_,
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(SendChatReq, _has_bits_),
      -1,
      -1,
      sizeof(SendChatReq),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(SendChatReq, _internal_metadata_));
  SendChatRsp_descriptor_ = file->message_type(1);
  static const int SendChatRsp_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(SendChatRsp, resultcode_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(SendChatRsp, messageinfoid_),
  };
  SendChatRsp_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      SendChatRsp_descriptor_,
      SendChatRsp::internal_default_instance(),
      SendChatRsp_offsets_,
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(SendChatRsp, _has_bits_),
      -1,
      -1,
      sizeof(SendChatRsp),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(SendChatRsp, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_SendChatMsg_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      SendChatReq_descriptor_, SendChatReq::internal_default_instance());
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      SendChatRsp_descriptor_, SendChatRsp::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_SendChatMsg_2eproto() {
  SendChatReq_default_instance_.Shutdown();
  delete SendChatReq_reflection_;
  SendChatRsp_default_instance_.Shutdown();
  delete SendChatRsp_reflection_;
}

void protobuf_InitDefaults_SendChatMsg_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::protocol::protobuf_InitDefaults_ChatData_2eproto();
  ::protocol::protobuf_InitDefaults_Result_2eproto();
  SendChatReq_default_instance_.DefaultConstruct();
  ::google::protobuf::internal::GetEmptyString();
  SendChatRsp_default_instance_.DefaultConstruct();
  SendChatReq_default_instance_.get_mutable()->InitAsDefaultInstance();
  SendChatRsp_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_SendChatMsg_2eproto_once_);
void protobuf_InitDefaults_SendChatMsg_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_SendChatMsg_2eproto_once_,
                 &protobuf_InitDefaults_SendChatMsg_2eproto_impl);
}
void protobuf_AddDesc_SendChatMsg_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_SendChatMsg_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\021SendChatMsg.proto\022\010protocol\032\016ChatData."
    "proto\032\014Result.proto\"3\n\013SendChatReq\022$\n\010ch"
    "atData\030\001 \002(\0132\022.protocol.ChatItem\"N\n\013Send"
    "ChatRsp\022(\n\nresultCode\030\001 \002(\0162\024.protocol.R"
    "esultCode\022\025\n\rmessageInfoID\030\002 \002(\t", 192);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "SendChatMsg.proto", &protobuf_RegisterTypes);
  ::protocol::protobuf_AddDesc_ChatData_2eproto();
  ::protocol::protobuf_AddDesc_Result_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_SendChatMsg_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_SendChatMsg_2eproto_once_);
void protobuf_AddDesc_SendChatMsg_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_SendChatMsg_2eproto_once_,
                 &protobuf_AddDesc_SendChatMsg_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_SendChatMsg_2eproto {
  StaticDescriptorInitializer_SendChatMsg_2eproto() {
    protobuf_AddDesc_SendChatMsg_2eproto();
  }
} static_descriptor_initializer_SendChatMsg_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int SendChatReq::kChatDataFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

SendChatReq::SendChatReq()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_SendChatMsg_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:protocol.SendChatReq)
}

void SendChatReq::InitAsDefaultInstance() {
  chatdata_ = const_cast< ::protocol::ChatItem*>(
      ::protocol::ChatItem::internal_default_instance());
}

SendChatReq::SendChatReq(const SendChatReq& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:protocol.SendChatReq)
}

void SendChatReq::SharedCtor() {
  _cached_size_ = 0;
  chatdata_ = NULL;
}

SendChatReq::~SendChatReq() {
  // @@protoc_insertion_point(destructor:protocol.SendChatReq)
  SharedDtor();
}

void SendChatReq::SharedDtor() {
  if (this != &SendChatReq_default_instance_.get()) {
    delete chatdata_;
  }
}

void SendChatReq::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* SendChatReq::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return SendChatReq_descriptor_;
}

const SendChatReq& SendChatReq::default_instance() {
  protobuf_InitDefaults_SendChatMsg_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<SendChatReq> SendChatReq_default_instance_;

SendChatReq* SendChatReq::New(::google::protobuf::Arena* arena) const {
  SendChatReq* n = new SendChatReq;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void SendChatReq::Clear() {
// @@protoc_insertion_point(message_clear_start:protocol.SendChatReq)
  if (has_chatdata()) {
    if (chatdata_ != NULL) chatdata_->::protocol::ChatItem::Clear();
  }
  _has_bits_.Clear();
  if (_internal_metadata_.have_unknown_fields()) {
    mutable_unknown_fields()->Clear();
  }
}

bool SendChatReq::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:protocol.SendChatReq)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // required .protocol.ChatItem chatData = 1;
      case 1: {
        if (tag == 10) {
          DO_(::google::protobuf::internal::WireFormatLite::ReadMessageNoVirtual(
               input, mutable_chatdata()));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectAtEnd()) goto success;
        break;
      }

      default: {
      handle_unusual:
        if (tag == 0 ||
            ::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_END_GROUP) {
          goto success;
        }
        DO_(::google::protobuf::internal::WireFormat::SkipField(
              input, tag, mutable_unknown_fields()));
        break;
      }
    }
  }
success:
  // @@protoc_insertion_point(parse_success:protocol.SendChatReq)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:protocol.SendChatReq)
  return false;
#undef DO_
}

void SendChatReq::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:protocol.SendChatReq)
  // required .protocol.ChatItem chatData = 1;
  if (has_chatdata()) {
    ::google::protobuf::internal::WireFormatLite::WriteMessageMaybeToArray(
      1, *this->chatdata_, output);
  }

  if (_internal_metadata_.have_unknown_fields()) {
    ::google::protobuf::internal::WireFormat::SerializeUnknownFields(
        unknown_fields(), output);
  }
  // @@protoc_insertion_point(serialize_end:protocol.SendChatReq)
}

::google::protobuf::uint8* SendChatReq::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:protocol.SendChatReq)
  // required .protocol.ChatItem chatData = 1;
  if (has_chatdata()) {
    target = ::google::protobuf::internal::WireFormatLite::
      InternalWriteMessageNoVirtualToArray(
        1, *this->chatdata_, false, target);
  }

  if (_internal_metadata_.have_unknown_fields()) {
    target = ::google::protobuf::internal::WireFormat::SerializeUnknownFieldsToArray(
        unknown_fields(), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:protocol.SendChatReq)
  return target;
}

size_t SendChatReq::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:protocol.SendChatReq)
  size_t total_size = 0;

  // required .protocol.ChatItem chatData = 1;
  if (has_chatdata()) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::MessageSizeNoVirtual(
        *this->chatdata_);
  }
  if (_internal_metadata_.have_unknown_fields()) {
    total_size +=
      ::google::protobuf::internal::WireFormat::ComputeUnknownFieldsSize(
        unknown_fields());
  }
  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void SendChatReq::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:protocol.SendChatReq)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const SendChatReq* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const SendChatReq>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:protocol.SendChatReq)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:protocol.SendChatReq)
    UnsafeMergeFrom(*source);
  }
}

void SendChatReq::MergeFrom(const SendChatReq& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:protocol.SendChatReq)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void SendChatReq::UnsafeMergeFrom(const SendChatReq& from) {
  GOOGLE_DCHECK(&from != this);
  if (from._has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    if (from.has_chatdata()) {
      mutable_chatdata()->::protocol::ChatItem::MergeFrom(from.chatdata());
    }
  }
  if (from._internal_metadata_.have_unknown_fields()) {
    ::google::protobuf::UnknownFieldSet::MergeToInternalMetdata(
      from.unknown_fields(), &_internal_metadata_);
  }
}

void SendChatReq::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:protocol.SendChatReq)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void SendChatReq::CopyFrom(const SendChatReq& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:protocol.SendChatReq)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool SendChatReq::IsInitialized() const {
  if ((_has_bits_[0] & 0x00000001) != 0x00000001) return false;

  if (has_chatdata()) {
    if (!this->chatdata_->IsInitialized()) return false;
  }
  return true;
}

void SendChatReq::Swap(SendChatReq* other) {
  if (other == this) return;
  InternalSwap(other);
}
void SendChatReq::InternalSwap(SendChatReq* other) {
  std::swap(chatdata_, other->chatdata_);
  std::swap(_has_bits_[0], other->_has_bits_[0]);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata SendChatReq::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = SendChatReq_descriptor_;
  metadata.reflection = SendChatReq_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// SendChatReq

// required .protocol.ChatItem chatData = 1;
bool SendChatReq::has_chatdata() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
void SendChatReq::set_has_chatdata() {
  _has_bits_[0] |= 0x00000001u;
}
void SendChatReq::clear_has_chatdata() {
  _has_bits_[0] &= ~0x00000001u;
}
void SendChatReq::clear_chatdata() {
  if (chatdata_ != NULL) chatdata_->::protocol::ChatItem::Clear();
  clear_has_chatdata();
}
const ::protocol::ChatItem& SendChatReq::chatdata() const {
  // @@protoc_insertion_point(field_get:protocol.SendChatReq.chatData)
  return chatdata_ != NULL ? *chatdata_
                         : *::protocol::ChatItem::internal_default_instance();
}
::protocol::ChatItem* SendChatReq::mutable_chatdata() {
  set_has_chatdata();
  if (chatdata_ == NULL) {
    chatdata_ = new ::protocol::ChatItem;
  }
  // @@protoc_insertion_point(field_mutable:protocol.SendChatReq.chatData)
  return chatdata_;
}
::protocol::ChatItem* SendChatReq::release_chatdata() {
  // @@protoc_insertion_point(field_release:protocol.SendChatReq.chatData)
  clear_has_chatdata();
  ::protocol::ChatItem* temp = chatdata_;
  chatdata_ = NULL;
  return temp;
}
void SendChatReq::set_allocated_chatdata(::protocol::ChatItem* chatdata) {
  delete chatdata_;
  chatdata_ = chatdata;
  if (chatdata) {
    set_has_chatdata();
  } else {
    clear_has_chatdata();
  }
  // @@protoc_insertion_point(field_set_allocated:protocol.SendChatReq.chatData)
}

inline const SendChatReq* SendChatReq::internal_default_instance() {
  return &SendChatReq_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int SendChatRsp::kResultCodeFieldNumber;
const int SendChatRsp::kMessageInfoIDFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

SendChatRsp::SendChatRsp()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_SendChatMsg_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:protocol.SendChatRsp)
}

void SendChatRsp::InitAsDefaultInstance() {
}

SendChatRsp::SendChatRsp(const SendChatRsp& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:protocol.SendChatRsp)
}

void SendChatRsp::SharedCtor() {
  _cached_size_ = 0;
  messageinfoid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  resultcode_ = 0;
}

SendChatRsp::~SendChatRsp() {
  // @@protoc_insertion_point(destructor:protocol.SendChatRsp)
  SharedDtor();
}

void SendChatRsp::SharedDtor() {
  messageinfoid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void SendChatRsp::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* SendChatRsp::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return SendChatRsp_descriptor_;
}

const SendChatRsp& SendChatRsp::default_instance() {
  protobuf_InitDefaults_SendChatMsg_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<SendChatRsp> SendChatRsp_default_instance_;

SendChatRsp* SendChatRsp::New(::google::protobuf::Arena* arena) const {
  SendChatRsp* n = new SendChatRsp;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void SendChatRsp::Clear() {
// @@protoc_insertion_point(message_clear_start:protocol.SendChatRsp)
  if (_has_bits_[0 / 32] & 3u) {
    resultcode_ = 0;
    if (has_messageinfoid()) {
      messageinfoid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
    }
  }
  _has_bits_.Clear();
  if (_internal_metadata_.have_unknown_fields()) {
    mutable_unknown_fields()->Clear();
  }
}

bool SendChatRsp::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:protocol.SendChatRsp)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // required .protocol.ResultCode resultCode = 1;
      case 1: {
        if (tag == 8) {
          int value;
          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   int, ::google::protobuf::internal::WireFormatLite::TYPE_ENUM>(
                 input, &value)));
          if (::protocol::ResultCode_IsValid(value)) {
            set_resultcode(static_cast< ::protocol::ResultCode >(value));
          } else {
            mutable_unknown_fields()->AddVarint(1, value);
          }
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_messageInfoID;
        break;
      }

      // required string messageInfoID = 2;
      case 2: {
        if (tag == 18) {
         parse_messageInfoID:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_messageinfoid()));
          ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
            this->messageinfoid().data(), this->messageinfoid().length(),
            ::google::protobuf::internal::WireFormat::PARSE,
            "protocol.SendChatRsp.messageInfoID");
        } else {
          goto handle_unusual;
        }
        if (input->ExpectAtEnd()) goto success;
        break;
      }

      default: {
      handle_unusual:
        if (tag == 0 ||
            ::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_END_GROUP) {
          goto success;
        }
        DO_(::google::protobuf::internal::WireFormat::SkipField(
              input, tag, mutable_unknown_fields()));
        break;
      }
    }
  }
success:
  // @@protoc_insertion_point(parse_success:protocol.SendChatRsp)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:protocol.SendChatRsp)
  return false;
#undef DO_
}

void SendChatRsp::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:protocol.SendChatRsp)
  // required .protocol.ResultCode resultCode = 1;
  if (has_resultcode()) {
    ::google::protobuf::internal::WireFormatLite::WriteEnum(
      1, this->resultcode(), output);
  }

  // required string messageInfoID = 2;
  if (has_messageinfoid()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->messageinfoid().data(), this->messageinfoid().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.SendChatRsp.messageInfoID");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->messageinfoid(), output);
  }

  if (_internal_metadata_.have_unknown_fields()) {
    ::google::protobuf::internal::WireFormat::SerializeUnknownFields(
        unknown_fields(), output);
  }
  // @@protoc_insertion_point(serialize_end:protocol.SendChatRsp)
}

::google::protobuf::uint8* SendChatRsp::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:protocol.SendChatRsp)
  // required .protocol.ResultCode resultCode = 1;
  if (has_resultcode()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteEnumToArray(
      1, this->resultcode(), target);
  }

  // required string messageInfoID = 2;
  if (has_messageinfoid()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->messageinfoid().data(), this->messageinfoid().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.SendChatRsp.messageInfoID");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->messageinfoid(), target);
  }

  if (_internal_metadata_.have_unknown_fields()) {
    target = ::google::protobuf::internal::WireFormat::SerializeUnknownFieldsToArray(
        unknown_fields(), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:protocol.SendChatRsp)
  return target;
}

size_t SendChatRsp::RequiredFieldsByteSizeFallback() const {
// @@protoc_insertion_point(required_fields_byte_size_fallback_start:protocol.SendChatRsp)
  size_t total_size = 0;

  if (has_resultcode()) {
    // required .protocol.ResultCode resultCode = 1;
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::EnumSize(this->resultcode());
  }

  if (has_messageinfoid()) {
    // required string messageInfoID = 2;
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->messageinfoid());
  }

  return total_size;
}
size_t SendChatRsp::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:protocol.SendChatRsp)
  size_t total_size = 0;

  if (((_has_bits_[0] & 0x00000003) ^ 0x00000003) == 0) {  // All required fields are present.
    // required .protocol.ResultCode resultCode = 1;
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::EnumSize(this->resultcode());

    // required string messageInfoID = 2;
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->messageinfoid());

  } else {
    total_size += RequiredFieldsByteSizeFallback();
  }
  if (_internal_metadata_.have_unknown_fields()) {
    total_size +=
      ::google::protobuf::internal::WireFormat::ComputeUnknownFieldsSize(
        unknown_fields());
  }
  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void SendChatRsp::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:protocol.SendChatRsp)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const SendChatRsp* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const SendChatRsp>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:protocol.SendChatRsp)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:protocol.SendChatRsp)
    UnsafeMergeFrom(*source);
  }
}

void SendChatRsp::MergeFrom(const SendChatRsp& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:protocol.SendChatRsp)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void SendChatRsp::UnsafeMergeFrom(const SendChatRsp& from) {
  GOOGLE_DCHECK(&from != this);
  if (from._has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    if (from.has_resultcode()) {
      set_resultcode(from.resultcode());
    }
    if (from.has_messageinfoid()) {
      set_has_messageinfoid();
      messageinfoid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.messageinfoid_);
    }
  }
  if (from._internal_metadata_.have_unknown_fields()) {
    ::google::protobuf::UnknownFieldSet::MergeToInternalMetdata(
      from.unknown_fields(), &_internal_metadata_);
  }
}

void SendChatRsp::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:protocol.SendChatRsp)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void SendChatRsp::CopyFrom(const SendChatRsp& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:protocol.SendChatRsp)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool SendChatRsp::IsInitialized() const {
  if ((_has_bits_[0] & 0x00000003) != 0x00000003) return false;

  return true;
}

void SendChatRsp::Swap(SendChatRsp* other) {
  if (other == this) return;
  InternalSwap(other);
}
void SendChatRsp::InternalSwap(SendChatRsp* other) {
  std::swap(resultcode_, other->resultcode_);
  messageinfoid_.Swap(&other->messageinfoid_);
  std::swap(_has_bits_[0], other->_has_bits_[0]);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata SendChatRsp::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = SendChatRsp_descriptor_;
  metadata.reflection = SendChatRsp_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// SendChatRsp

// required .protocol.ResultCode resultCode = 1;
bool SendChatRsp::has_resultcode() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
void SendChatRsp::set_has_resultcode() {
  _has_bits_[0] |= 0x00000001u;
}
void SendChatRsp::clear_has_resultcode() {
  _has_bits_[0] &= ~0x00000001u;
}
void SendChatRsp::clear_resultcode() {
  resultcode_ = 0;
  clear_has_resultcode();
}
::protocol::ResultCode SendChatRsp::resultcode() const {
  // @@protoc_insertion_point(field_get:protocol.SendChatRsp.resultCode)
  return static_cast< ::protocol::ResultCode >(resultcode_);
}
void SendChatRsp::set_resultcode(::protocol::ResultCode value) {
  assert(::protocol::ResultCode_IsValid(value));
  set_has_resultcode();
  resultcode_ = value;
  // @@protoc_insertion_point(field_set:protocol.SendChatRsp.resultCode)
}

// required string messageInfoID = 2;
bool SendChatRsp::has_messageinfoid() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
void SendChatRsp::set_has_messageinfoid() {
  _has_bits_[0] |= 0x00000002u;
}
void SendChatRsp::clear_has_messageinfoid() {
  _has_bits_[0] &= ~0x00000002u;
}
void SendChatRsp::clear_messageinfoid() {
  messageinfoid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_messageinfoid();
}
const ::std::string& SendChatRsp::messageinfoid() const {
  // @@protoc_insertion_point(field_get:protocol.SendChatRsp.messageInfoID)
  return messageinfoid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void SendChatRsp::set_messageinfoid(const ::std::string& value) {
  set_has_messageinfoid();
  messageinfoid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:protocol.SendChatRsp.messageInfoID)
}
void SendChatRsp::set_messageinfoid(const char* value) {
  set_has_messageinfoid();
  messageinfoid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:protocol.SendChatRsp.messageInfoID)
}
void SendChatRsp::set_messageinfoid(const char* value, size_t size) {
  set_has_messageinfoid();
  messageinfoid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:protocol.SendChatRsp.messageInfoID)
}
::std::string* SendChatRsp::mutable_messageinfoid() {
  set_has_messageinfoid();
  // @@protoc_insertion_point(field_mutable:protocol.SendChatRsp.messageInfoID)
  return messageinfoid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* SendChatRsp::release_messageinfoid() {
  // @@protoc_insertion_point(field_release:protocol.SendChatRsp.messageInfoID)
  clear_has_messageinfoid();
  return messageinfoid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void SendChatRsp::set_allocated_messageinfoid(::std::string* messageinfoid) {
  if (messageinfoid != NULL) {
    set_has_messageinfoid();
  } else {
    clear_has_messageinfoid();
  }
  messageinfoid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), messageinfoid);
  // @@protoc_insertion_point(field_set_allocated:protocol.SendChatRsp.messageInfoID)
}

inline const SendChatRsp* SendChatRsp::internal_default_instance() {
  return &SendChatRsp_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace protocol

// @@protoc_insertion_point(global_scope)
