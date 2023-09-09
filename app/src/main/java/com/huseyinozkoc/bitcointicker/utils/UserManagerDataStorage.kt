package com.huseyinozkoc.bitcointicker.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Normal DataStore Preference Example
class UserManagerDataStorage(var context: Context) {

    //Create the dataStore
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")

    //Create some keys
    companion object {
        val USER_AGE_KEY = intPreferencesKey("USER_AGE")
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
        val USER_GENDER_KEY = stringPreferencesKey("USER_GENDER")
        val USER_EMAIL_KEY = stringPreferencesKey("USER_EMAIL_KEY")
        val USER_PHONE_KEY = stringPreferencesKey("USER_PHONE_KEY")
        val USER_UUID_KEY = stringPreferencesKey("USER_UUID_KEY")
        val USER_PROFILE_PIC_KEY = stringPreferencesKey("USER_PROFILE_PIC_KEY")
        val USER_FULL_NAME_KEY = stringPreferencesKey("USER_FULL_NAME_KEY")
        val USER_ADDRESS_KEY = stringPreferencesKey("USER_ADDRESS_KEY")
        val USER_BIO_KEY = stringPreferencesKey("USER_BIO_KEY")
        val ON_BOARDING_PASSED = booleanPreferencesKey("ON_BOARDING_PASSED")


    }

    //Store user data
    suspend fun storeUser(age: Int, name: String, isMale: String) {
        context.dataStore.edit {
            it[USER_AGE_KEY] = age
            it[USER_NAME_KEY] = name
            it[USER_GENDER_KEY] = isMale
        }

    }

    // Store user age
    suspend fun storeUserAge(age: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_AGE_KEY] = age
        }
    }

    // Store user name
    suspend fun storeUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    // Store user gender
    suspend fun storeUserGender(gender: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_GENDER_KEY] = gender
        }
    }

    // Store user email
    suspend fun storeUserEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    // Store user phone
    suspend fun storeUserPhone(phone: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PHONE_KEY] = phone
        }
    }

    // Store user UUID
    suspend fun storeUserUUID(uuid: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_UUID_KEY] = uuid
        }
    }

    // Store user profile picture
    suspend fun storeUserProfilePic(profilePicUrl: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PROFILE_PIC_KEY] = profilePicUrl
        }
    }

    // Store user full name
    suspend fun storeUserFullName(fullName: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_FULL_NAME_KEY] = fullName
        }
    }

    // Store user address
    suspend fun storeUserAddress(address: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ADDRESS_KEY] = address
        }
    }

    // Store user bio
    suspend fun storeUserBio(bio: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_BIO_KEY] = bio
        }
    }

    // Store On Boarding value
    suspend fun storeOnboardingPassed(passed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ON_BOARDING_PASSED] = passed
        }
    }



    //----------------------------------------------------------------------------------------------
    // Bu alan getterları içeriyor.

    // Retrieve functions
    suspend fun getNameFromStorage(): String {
        return context.dataStore.data.first()[USER_NAME_KEY]  ?: ""
    }

    suspend fun getAgeFromStorage(): Int {
        return context.dataStore.data.first()[USER_AGE_KEY] ?: 0
    }

    suspend fun getGenderFromStorage(): String {
        return context.dataStore.data.first()[USER_GENDER_KEY]  ?: ""
    }

    suspend fun getEmailFromStorage(): String {
        return context.dataStore.data.first()[USER_EMAIL_KEY]  ?: ""
    }

    suspend fun getPhoneFromStorage(): String {
        return context.dataStore.data.first()[USER_PHONE_KEY]  ?: ""
    }

    suspend fun getUUIDFromStorage(): String {
        return context.dataStore.data.first()[USER_UUID_KEY]  ?: ""
    }

    suspend fun getProfilePicFromStorage(): String {
        return context.dataStore.data.first()[USER_PROFILE_PIC_KEY]  ?: ""
    }

    suspend fun getFullNameFromStorage(): String {
        return context.dataStore.data.first()[USER_FULL_NAME_KEY]  ?: ""
    }

    suspend fun getAddressFromStorage(): String {
        return context.dataStore.data.first()[USER_ADDRESS_KEY]  ?: ""
    }

    suspend fun getBioFromStorage(): String {
        return context.dataStore.data.first()[USER_BIO_KEY] ?: ""
    }

    suspend fun getOnboardingPassedFromStorage(): Boolean {
        return context.dataStore.data.first()[ON_BOARDING_PASSED] ?: false
    }


    //----------------------------------------------------------------------------------------------
    // Bu alan dataların Flowları içindir.

    // Create flows

    val userNameFlow = context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: ""
    }

    val userGenderFlow= context.dataStore.data.map { preferences ->
        preferences[USER_GENDER_KEY] ?: "Others"
    }

    val userEmailFlow = context.dataStore.data.map { preferences ->
        preferences[USER_EMAIL_KEY] ?: ""
    }

    val userPhoneFlow = context.dataStore.data.map { preferences ->
        preferences[USER_PHONE_KEY] ?: ""
    }

    val userUUIDFlow= context.dataStore.data.map { preferences ->
        preferences[USER_UUID_KEY] ?: ""
    }

    val userProfilePicFlow = context.dataStore.data.map { preferences ->
        preferences[USER_PROFILE_PIC_KEY] ?: ""
    }

    val userFullNameFlow = context.dataStore.data.map { preferences ->
        preferences[USER_FULL_NAME_KEY] ?: ""
    }

    val userAddressFlow= context.dataStore.data.map { preferences ->
        preferences[USER_ADDRESS_KEY] ?: ""
    }

    val userBioFlow = context.dataStore.data.map { preferences ->
        preferences[USER_BIO_KEY] ?: ""
    }

    val onboardingPassedFlow= context.dataStore.data.map { preferences ->
        preferences[ON_BOARDING_PASSED] ?: false
    }

}