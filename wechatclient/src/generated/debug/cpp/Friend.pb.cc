// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Friend.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "Friend.pb.h"

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

const ::google::protobuf::Descriptor* FriendItem_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  FriendItem_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_Friend_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_Friend_2eproto() {
  protobuf_AddDesc_Friend_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "Friend.proto");
  GOOGLE_CHECK(file != NULL);
  FriendItem_descriptor_ = file->message_type(0);
  static const int FriendItem_offsets_[5] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(FriendItem, userid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(FriendItem, friendid_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(FriendItem, friendname_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(FriendItem, headpath_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(FriendItem, creattime_),
  };
  FriendItem_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      FriendItem_descriptor_,
      FriendItem::internal_default_instance(),
      FriendItem_offsets_,
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(FriendItem, _has_bits_),
      -1,
      -1,
      sizeof(FriendItem),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(FriendItem, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_Friend_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      FriendItem_descriptor_, FriendItem::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_Friend_2eproto() {
  FriendItem_default_instance_.Shutdown();
  delete FriendItem_reflection_;
}

void protobuf_InitDefaults_Friend_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::internal::GetEmptyString();
  FriendItem_default_instance_.DefaultConstruct();
  FriendItem_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_Friend_2eproto_once_);
void protobuf_InitDefaults_Friend_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_Friend_2eproto_once_,
                 &protobuf_InitDefaults_Friend_2eproto_impl);
}
void protobuf_AddDesc_Friend_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_Friend_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\014Friend.proto\022\010protocol\"g\n\nFriendItem\022\016"
    "\n\006userId\030\001 \002(\t\022\020\n\010friendId\030\002 \002(\t\022\022\n\nfrie"
    "ndName\030\003 \001(\t\022\020\n\010headPath\030\004 \001(\t\022\021\n\tcreatT"
    "ime\030\005 \001(\003", 129);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "Friend.proto", &protobuf_RegisterTypes);
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_Friend_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_Friend_2eproto_once_);
void protobuf_AddDesc_Friend_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_Friend_2eproto_once_,
                 &protobuf_AddDesc_Friend_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_Friend_2eproto {
  StaticDescriptorInitializer_Friend_2eproto() {
    protobuf_AddDesc_Friend_2eproto();
  }
} static_descriptor_initializer_Friend_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int FriendItem::kUserIdFieldNumber;
const int FriendItem::kFriendIdFieldNumber;
const int FriendItem::kFriendNameFieldNumber;
const int FriendItem::kHeadPathFieldNumber;
const int FriendItem::kCreatTimeFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

FriendItem::FriendItem()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_Friend_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:protocol.FriendItem)
}

void FriendItem::InitAsDefaultInstance() {
}

FriendItem::FriendItem(const FriendItem& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:protocol.FriendItem)
}

void FriendItem::SharedCtor() {
  _cached_size_ = 0;
  userid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  friendid_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  friendname_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  headpath_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  creattime_ = GOOGLE_LONGLONG(0);
}

FriendItem::~FriendItem() {
  // @@protoc_insertion_point(destructor:protocol.FriendItem)
  SharedDtor();
}

void FriendItem::SharedDtor() {
  userid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  friendid_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  friendname_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  headpath_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void FriendItem::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* FriendItem::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return FriendItem_descriptor_;
}

const FriendItem& FriendItem::default_instance() {
  protobuf_InitDefaults_Friend_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<FriendItem> FriendItem_default_instance_;

FriendItem* FriendItem::New(::google::protobuf::Arena* arena) const {
  FriendItem* n = new FriendItem;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void FriendItem::Clear() {
// @@protoc_insertion_point(message_clear_start:protocol.FriendItem)
  if (_has_bits_[0 / 32] & 31u) {
    if (has_userid()) {
      userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
    }
    if (has_friendid()) {
      friendid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
    }
    if (has_friendname()) {
      friendname_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
    }
    if (has_headpath()) {
      headpath_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
    }
    creattime_ = GOOGLE_LONGLONG(0);
  }
  _has_bits_.Clear();
  if (_internal_metadata_.have_unknown_fields()) {
    mutable_unknown_fields()->Clear();
  }
}

bool FriendItem::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:protocol.FriendItem)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // required string userId = 1;
      case 1: {
        if (tag == 10) {
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_userid()));
          ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
            this->userid().data(), this->userid().length(),
            ::google::protobuf::internal::WireFormat::PARSE,
            "protocol.FriendItem.userId");
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_friendId;
        break;
      }

      // required string friendId = 2;
      case 2: {
        if (tag == 18) {
         parse_friendId:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_friendid()));
          ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
            this->friendid().data(), this->friendid().length(),
            ::google::protobuf::internal::WireFormat::PARSE,
            "protocol.FriendItem.friendId");
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(26)) goto parse_friendName;
        break;
      }

      // optional string friendName = 3;
      case 3: {
        if (tag == 26) {
         parse_friendName:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_friendname()));
          ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
            this->friendname().data(), this->friendname().length(),
            ::google::protobuf::internal::WireFormat::PARSE,
            "protocol.FriendItem.friendName");
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(34)) goto parse_headPath;
        break;
      }

      // optional string headPath = 4;
      case 4: {
        if (tag == 34) {
         parse_headPath:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_headpath()));
          ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
            this->headpath().data(), this->headpath().length(),
            ::google::protobuf::internal::WireFormat::PARSE,
            "protocol.FriendItem.headPath");
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(40)) goto parse_creatTime;
        break;
      }

      // optional int64 creatTime = 5;
      case 5: {
        if (tag == 40) {
         parse_creatTime:
          set_has_creattime();
          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::int64, ::google::protobuf::internal::WireFormatLite::TYPE_INT64>(
                 input, &creattime_)));
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
  // @@protoc_insertion_point(parse_success:protocol.FriendItem)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:protocol.FriendItem)
  return false;
#undef DO_
}

void FriendItem::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:protocol.FriendItem)
  // required string userId = 1;
  if (has_userid()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->userid().data(), this->userid().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.FriendItem.userId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      1, this->userid(), output);
  }

  // required string friendId = 2;
  if (has_friendid()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->friendid().data(), this->friendid().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.FriendItem.friendId");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->friendid(), output);
  }

  // optional string friendName = 3;
  if (has_friendname()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->friendname().data(), this->friendname().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.FriendItem.friendName");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      3, this->friendname(), output);
  }

  // optional string headPath = 4;
  if (has_headpath()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->headpath().data(), this->headpath().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.FriendItem.headPath");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      4, this->headpath(), output);
  }

  // optional int64 creatTime = 5;
  if (has_creattime()) {
    ::google::protobuf::internal::WireFormatLite::WriteInt64(5, this->creattime(), output);
  }

  if (_internal_metadata_.have_unknown_fields()) {
    ::google::protobuf::internal::WireFormat::SerializeUnknownFields(
        unknown_fields(), output);
  }
  // @@protoc_insertion_point(serialize_end:protocol.FriendItem)
}

::google::protobuf::uint8* FriendItem::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:protocol.FriendItem)
  // required string userId = 1;
  if (has_userid()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->userid().data(), this->userid().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.FriendItem.userId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        1, this->userid(), target);
  }

  // required string friendId = 2;
  if (has_friendid()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->friendid().data(), this->friendid().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.FriendItem.friendId");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->friendid(), target);
  }

  // optional string friendName = 3;
  if (has_friendname()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->friendname().data(), this->friendname().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.FriendItem.friendName");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        3, this->friendname(), target);
  }

  // optional string headPath = 4;
  if (has_headpath()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8StringNamedField(
      this->headpath().data(), this->headpath().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE,
      "protocol.FriendItem.headPath");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        4, this->headpath(), target);
  }

  // optional int64 creatTime = 5;
  if (has_creattime()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArray(5, this->creattime(), target);
  }

  if (_internal_metadata_.have_unknown_fields()) {
    target = ::google::protobuf::internal::WireFormat::SerializeUnknownFieldsToArray(
        unknown_fields(), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:protocol.FriendItem)
  return target;
}

size_t FriendItem::RequiredFieldsByteSizeFallback() const {
// @@protoc_insertion_point(required_fields_byte_size_fallback_start:protocol.FriendItem)
  size_t total_size = 0;

  if (has_userid()) {
    // required string userId = 1;
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->userid());
  }

  if (has_friendid()) {
    // required string friendId = 2;
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->friendid());
  }

  return total_size;
}
size_t FriendItem::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:protocol.FriendItem)
  size_t total_size = 0;

  if (((_has_bits_[0] & 0x00000003) ^ 0x00000003) == 0) {  // All required fields are present.
    // required string userId = 1;
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->userid());

    // required string friendId = 2;
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->friendid());

  } else {
    total_size += RequiredFieldsByteSizeFallback();
  }
  if (_has_bits_[2 / 32] & 28u) {
    // optional string friendName = 3;
    if (has_friendname()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::StringSize(
          this->friendname());
    }

    // optional string headPath = 4;
    if (has_headpath()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::StringSize(
          this->headpath());
    }

    // optional int64 creatTime = 5;
    if (has_creattime()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::Int64Size(
          this->creattime());
    }

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

void FriendItem::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:protocol.FriendItem)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const FriendItem* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const FriendItem>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:protocol.FriendItem)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:protocol.FriendItem)
    UnsafeMergeFrom(*source);
  }
}

void FriendItem::MergeFrom(const FriendItem& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:protocol.FriendItem)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void FriendItem::UnsafeMergeFrom(const FriendItem& from) {
  GOOGLE_DCHECK(&from != this);
  if (from._has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    if (from.has_userid()) {
      set_has_userid();
      userid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.userid_);
    }
    if (from.has_friendid()) {
      set_has_friendid();
      friendid_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.friendid_);
    }
    if (from.has_friendname()) {
      set_has_friendname();
      friendname_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.friendname_);
    }
    if (from.has_headpath()) {
      set_has_headpath();
      headpath_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.headpath_);
    }
    if (from.has_creattime()) {
      set_creattime(from.creattime());
    }
  }
  if (from._internal_metadata_.have_unknown_fields()) {
    ::google::protobuf::UnknownFieldSet::MergeToInternalMetdata(
      from.unknown_fields(), &_internal_metadata_);
  }
}

void FriendItem::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:protocol.FriendItem)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void FriendItem::CopyFrom(const FriendItem& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:protocol.FriendItem)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool FriendItem::IsInitialized() const {
  if ((_has_bits_[0] & 0x00000003) != 0x00000003) return false;

  return true;
}

void FriendItem::Swap(FriendItem* other) {
  if (other == this) return;
  InternalSwap(other);
}
void FriendItem::InternalSwap(FriendItem* other) {
  userid_.Swap(&other->userid_);
  friendid_.Swap(&other->friendid_);
  friendname_.Swap(&other->friendname_);
  headpath_.Swap(&other->headpath_);
  std::swap(creattime_, other->creattime_);
  std::swap(_has_bits_[0], other->_has_bits_[0]);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata FriendItem::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = FriendItem_descriptor_;
  metadata.reflection = FriendItem_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// FriendItem

// required string userId = 1;
bool FriendItem::has_userid() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
void FriendItem::set_has_userid() {
  _has_bits_[0] |= 0x00000001u;
}
void FriendItem::clear_has_userid() {
  _has_bits_[0] &= ~0x00000001u;
}
void FriendItem::clear_userid() {
  userid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_userid();
}
const ::std::string& FriendItem::userid() const {
  // @@protoc_insertion_point(field_get:protocol.FriendItem.userId)
  return userid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void FriendItem::set_userid(const ::std::string& value) {
  set_has_userid();
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:protocol.FriendItem.userId)
}
void FriendItem::set_userid(const char* value) {
  set_has_userid();
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:protocol.FriendItem.userId)
}
void FriendItem::set_userid(const char* value, size_t size) {
  set_has_userid();
  userid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:protocol.FriendItem.userId)
}
::std::string* FriendItem::mutable_userid() {
  set_has_userid();
  // @@protoc_insertion_point(field_mutable:protocol.FriendItem.userId)
  return userid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* FriendItem::release_userid() {
  // @@protoc_insertion_point(field_release:protocol.FriendItem.userId)
  clear_has_userid();
  return userid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void FriendItem::set_allocated_userid(::std::string* userid) {
  if (userid != NULL) {
    set_has_userid();
  } else {
    clear_has_userid();
  }
  userid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), userid);
  // @@protoc_insertion_point(field_set_allocated:protocol.FriendItem.userId)
}

// required string friendId = 2;
bool FriendItem::has_friendid() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
void FriendItem::set_has_friendid() {
  _has_bits_[0] |= 0x00000002u;
}
void FriendItem::clear_has_friendid() {
  _has_bits_[0] &= ~0x00000002u;
}
void FriendItem::clear_friendid() {
  friendid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_friendid();
}
const ::std::string& FriendItem::friendid() const {
  // @@protoc_insertion_point(field_get:protocol.FriendItem.friendId)
  return friendid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void FriendItem::set_friendid(const ::std::string& value) {
  set_has_friendid();
  friendid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:protocol.FriendItem.friendId)
}
void FriendItem::set_friendid(const char* value) {
  set_has_friendid();
  friendid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:protocol.FriendItem.friendId)
}
void FriendItem::set_friendid(const char* value, size_t size) {
  set_has_friendid();
  friendid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:protocol.FriendItem.friendId)
}
::std::string* FriendItem::mutable_friendid() {
  set_has_friendid();
  // @@protoc_insertion_point(field_mutable:protocol.FriendItem.friendId)
  return friendid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* FriendItem::release_friendid() {
  // @@protoc_insertion_point(field_release:protocol.FriendItem.friendId)
  clear_has_friendid();
  return friendid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void FriendItem::set_allocated_friendid(::std::string* friendid) {
  if (friendid != NULL) {
    set_has_friendid();
  } else {
    clear_has_friendid();
  }
  friendid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), friendid);
  // @@protoc_insertion_point(field_set_allocated:protocol.FriendItem.friendId)
}

// optional string friendName = 3;
bool FriendItem::has_friendname() const {
  return (_has_bits_[0] & 0x00000004u) != 0;
}
void FriendItem::set_has_friendname() {
  _has_bits_[0] |= 0x00000004u;
}
void FriendItem::clear_has_friendname() {
  _has_bits_[0] &= ~0x00000004u;
}
void FriendItem::clear_friendname() {
  friendname_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_friendname();
}
const ::std::string& FriendItem::friendname() const {
  // @@protoc_insertion_point(field_get:protocol.FriendItem.friendName)
  return friendname_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void FriendItem::set_friendname(const ::std::string& value) {
  set_has_friendname();
  friendname_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:protocol.FriendItem.friendName)
}
void FriendItem::set_friendname(const char* value) {
  set_has_friendname();
  friendname_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:protocol.FriendItem.friendName)
}
void FriendItem::set_friendname(const char* value, size_t size) {
  set_has_friendname();
  friendname_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:protocol.FriendItem.friendName)
}
::std::string* FriendItem::mutable_friendname() {
  set_has_friendname();
  // @@protoc_insertion_point(field_mutable:protocol.FriendItem.friendName)
  return friendname_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* FriendItem::release_friendname() {
  // @@protoc_insertion_point(field_release:protocol.FriendItem.friendName)
  clear_has_friendname();
  return friendname_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void FriendItem::set_allocated_friendname(::std::string* friendname) {
  if (friendname != NULL) {
    set_has_friendname();
  } else {
    clear_has_friendname();
  }
  friendname_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), friendname);
  // @@protoc_insertion_point(field_set_allocated:protocol.FriendItem.friendName)
}

// optional string headPath = 4;
bool FriendItem::has_headpath() const {
  return (_has_bits_[0] & 0x00000008u) != 0;
}
void FriendItem::set_has_headpath() {
  _has_bits_[0] |= 0x00000008u;
}
void FriendItem::clear_has_headpath() {
  _has_bits_[0] &= ~0x00000008u;
}
void FriendItem::clear_headpath() {
  headpath_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_headpath();
}
const ::std::string& FriendItem::headpath() const {
  // @@protoc_insertion_point(field_get:protocol.FriendItem.headPath)
  return headpath_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void FriendItem::set_headpath(const ::std::string& value) {
  set_has_headpath();
  headpath_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:protocol.FriendItem.headPath)
}
void FriendItem::set_headpath(const char* value) {
  set_has_headpath();
  headpath_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:protocol.FriendItem.headPath)
}
void FriendItem::set_headpath(const char* value, size_t size) {
  set_has_headpath();
  headpath_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:protocol.FriendItem.headPath)
}
::std::string* FriendItem::mutable_headpath() {
  set_has_headpath();
  // @@protoc_insertion_point(field_mutable:protocol.FriendItem.headPath)
  return headpath_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* FriendItem::release_headpath() {
  // @@protoc_insertion_point(field_release:protocol.FriendItem.headPath)
  clear_has_headpath();
  return headpath_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void FriendItem::set_allocated_headpath(::std::string* headpath) {
  if (headpath != NULL) {
    set_has_headpath();
  } else {
    clear_has_headpath();
  }
  headpath_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), headpath);
  // @@protoc_insertion_point(field_set_allocated:protocol.FriendItem.headPath)
}

// optional int64 creatTime = 5;
bool FriendItem::has_creattime() const {
  return (_has_bits_[0] & 0x00000010u) != 0;
}
void FriendItem::set_has_creattime() {
  _has_bits_[0] |= 0x00000010u;
}
void FriendItem::clear_has_creattime() {
  _has_bits_[0] &= ~0x00000010u;
}
void FriendItem::clear_creattime() {
  creattime_ = GOOGLE_LONGLONG(0);
  clear_has_creattime();
}
::google::protobuf::int64 FriendItem::creattime() const {
  // @@protoc_insertion_point(field_get:protocol.FriendItem.creatTime)
  return creattime_;
}
void FriendItem::set_creattime(::google::protobuf::int64 value) {
  set_has_creattime();
  creattime_ = value;
  // @@protoc_insertion_point(field_set:protocol.FriendItem.creatTime)
}

inline const FriendItem* FriendItem::internal_default_instance() {
  return &FriendItem_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace protocol

// @@protoc_insertion_point(global_scope)
