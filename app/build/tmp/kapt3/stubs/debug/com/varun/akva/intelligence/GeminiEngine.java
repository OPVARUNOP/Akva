package com.varun.akva.intelligence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0016\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/varun/akva/intelligence/GeminiEngine;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "commandPrompt", "", "conversationPrompt", "observerPrompt", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "callBackend", "payload", "Lorg/json/JSONObject;", "classifyCommand", "Lcom/varun/akva/intelligence/CommandResult;", "userSpeech", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getConversationFallback", "ctx", "Lcom/varun/akva/intelligence/AkvaContext;", "getConversationResponse", "(Ljava/lang/String;Lcom/varun/akva/intelligence/AkvaContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocalFallback", "getObserverResponse", "(Lcom/varun/akva/intelligence/AkvaContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isNetworkAvailable", "", "Companion", "app_debug"})
public final class GeminiEngine {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.varun.akva.data.SettingsManager settingsManager = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "GeminiEngine";
    private static final int SERVER_TIMEOUT = 8000;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String observerPrompt = "You are AKVA, a warm AI companion on this Android phone.\nAn app just opened. You may choose to say something or stay silent.\n\nRules:\n- If you have something genuinely useful to say: say it in 1-2 sentences\n- If you have nothing useful to say: return exactly the word SILENT\n- Never generic filler. Only speak if it adds real value.\n- Sound human. Warm. Like a close friend who notices things.\n- Never say you are AI. Never mention Gemini or AKVA.\n- Mention unread messages only if count is above 0.\n- If opened 4 plus times today: gently notice the habit.\n- If after 10PM: acknowledge it is late only if relevant.";
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String conversationPrompt = "You are AKVA, a warm AI companion on this Android phone.\nThe user just spoke to you directly. Have a real conversation.\n\nRules:\n- Answer directly and helpfully. Max 3 sentences.\n- Sound human. Warm. Like a close friend.\n- Never say you are AI. Never mention Gemini or AKVA.\n- Use the phone context data to give accurate answers.\n- If asked about messages: use the unreadCount data.\n- If asked about battery: use the batteryPercent data.\n- If asked about time: use the hourOfDay data.\n- If asked to do something: say you will do it right now.\n- Be natural. Be brief. Be real.";
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String commandPrompt = "Classify this as a phone command. Return JSON only. No other text.\nUser said: \"{userSpeech}\"\n\nJSON format:\n{\n  \"isCommand\": true or false,\n  \"commandType\": \"OPEN_APP|CALL|MESSAGE|ALARM|SEARCH|NAVIGATE|PLAY_MUSIC|PAUSE_MUSIC|NEXT_SONG|PREV_SONG|ANSWER_CALL|END_CALL|TAKE_PHOTO|SET_REMINDER|WIFI_ON|WIFI_OFF|BLUETOOTH_ON|BLUETOOTH_OFF|VOLUME_UP|VOLUME_DOWN|MUTE|UNMUTE|FLASHLIGHT_ON|FLASHLIGHT_OFF|SCREENSHOT|READ_MESSAGES|CHECK_BATTERY|CHECK_TIME|CONVERSATION\",\n  \"appName\": \"app name or null\",\n  \"contactName\": \"contact name or null\",\n  \"messageText\": \"message content or null\",\n  \"searchQuery\": \"search query or null\",\n  \"alarmTime\": \"time string or null\",\n  \"destination\": \"place name or null\"\n}\n\nExamples:\n\"open instagram\" = OPEN_APP instagram\n\"call mom\" = CALL mom\n\"message rahul say hi\" = MESSAGE rahul hi\n\"alarm 7am\" = ALARM 7:00 AM\n\"search restaurants near me\" = SEARCH restaurants near me\n\"take me to airport\" = NAVIGATE airport\n\"answer\" or \"pick up\" = ANSWER_CALL\n\"hang up\" or \"end call\" = END_CALL\n\"turn on wifi\" = WIFI_ON\n\"flashlight on\" = FLASHLIGHT_ON\n\"volume up\" = VOLUME_UP\n\"screenshot\" = SCREENSHOT\n\"read my messages\" = READ_MESSAGES\n\"what time is it\" = CHECK_TIME\n\"how is my battery\" = CHECK_BATTERY\n\"I am feeling stressed\" = CONVERSATION (not a command)";
    @org.jetbrains.annotations.NotNull()
    public static final com.varun.akva.intelligence.GeminiEngine.Companion Companion = null;
    
    public GeminiEngine(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getObserverResponse(@org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AkvaContext ctx, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getConversationResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String userSpeech, @org.jetbrains.annotations.NotNull()
    com.varun.akva.intelligence.AkvaContext ctx, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object classifyCommand(@org.jetbrains.annotations.NotNull()
    java.lang.String userSpeech, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.varun.akva.intelligence.CommandResult> $completion) {
        return null;
    }
    
    private final java.lang.String callBackend(org.json.JSONObject payload) {
        return null;
    }
    
    private final boolean isNetworkAvailable() {
        return false;
    }
    
    private final java.lang.String getLocalFallback(com.varun.akva.intelligence.AkvaContext ctx) {
        return null;
    }
    
    private final java.lang.String getConversationFallback(java.lang.String userSpeech, com.varun.akva.intelligence.AkvaContext ctx) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/varun/akva/intelligence/GeminiEngine$Companion;", "", "()V", "SERVER_TIMEOUT", "", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}