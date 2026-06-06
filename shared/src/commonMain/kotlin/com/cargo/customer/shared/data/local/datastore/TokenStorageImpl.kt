package com.cargo.customer.shared.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class TokenStorageImpl(
    private val dataStore: DataStore<Preferences>
) : TokenStorage {

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = token
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        dataStore.edit { prefs ->
            prefs[REFRESH_TOKEN] = token
        }
    }

    override suspend fun getAccessToken(): String? {
        return "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJKal8zNGtvNG84OWQzY0N2dVg2cndWYy1Cb2J1Sk4yc2FzSUxZWHFQMzlzIn0.eyJleHAiOjE3ODA4NDgyNDYsImlhdCI6MTc4MDc2MTg0NiwianRpIjoib25ydHJvOjg4MzdjYTNkLTFiY2UtOWI1Zi04YzFmLTAyNTU5M2UxMmJmNSIsImlzcyI6Imh0dHBzOi8vY2FyZ28ubm9ydGhldXJvcGUuY2xvdWRhcHAuYXp1cmUuY29tL2tleWNsb2FrL3JlYWxtcy9jYXJnby1jdXN0b21lciIsImF1ZCI6WyJjYXJnby1iYWNrZW5kIiwiYWNjb3VudCJdLCJzdWIiOiI3YTU1NDRlYy01ODVkLTQ3ZDAtOGI1Ni1jMDRhMzJlMTRiNDgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJjYXJnby1iYWNrZW5kIiwic2lkIjoiZ3RVclBUNW5VWDNkMU40Y1QxOFBkMkhwIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIvKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1jYXJnby1jdXN0b21lciIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJjdXN0b21lciJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IkhlbmQgU2F5ZWQiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJoZW5kc2F5ZWQ4NTNAZ21haWwuY29tIiwiZ2l2ZW5fbmFtZSI6IkhlbmQiLCJmYW1pbHlfbmFtZSI6IlNheWVkIiwiZW1haWwiOiJoZW5kc2F5ZWQ4NTNAZ21haWwuY29tIn0.A-Mf-EsW6ie5tEto3flfBqUyx4st2Yj7c9WoOqAeysHygskgYRn98BDd128R33CeM2omKUYD7Z1VO5ytdRNS1x01amuTqnn2uhlZ_2Ztk0aA-uF7_gZjLS4PN7NgpMdKkkO3hujKcBteop0mlCkAWWqAhMAFxp0B7cyWVswot1VvkS1GI6rMC9OD-ScnKz8vEiesoLuIRAAg3H818M7vH9dA1lFzq3wtVGIBCR8JQVxEsT03PyUI-RhmEHiEHJ0lUfEgMjNhIBtOF0zXYls7x5KqtJYe6saE69fzxJMiec0qmkynQNrCQ_7xfGhIb9GBbGvUGqhRpE4ZmwWRWaGvjg"
    }

    override suspend fun getRefreshToken(): String? {
        return dataStore.data.first()[REFRESH_TOKEN]
    }

    override suspend fun clearTokens() {
        dataStore.edit { prefs ->
            prefs.remove(ACCESS_TOKEN)
            prefs.remove(REFRESH_TOKEN)
        }
    }

    private companion object{
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }
}