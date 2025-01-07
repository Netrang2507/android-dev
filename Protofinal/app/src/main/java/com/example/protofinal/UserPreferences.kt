package com.example.protofinal


import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<UserPreferencesProto.UserPreferences> {
    override val defaultValue: UserPreferencesProto.UserPreferences =
        UserPreferencesProto.UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferencesProto.UserPreferences {
        try {
            return UserPreferencesProto.UserPreferences.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(
        t: UserPreferencesProto.UserPreferences,
        output: OutputStream
    ) = t.writeTo(output)
}
