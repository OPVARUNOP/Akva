package com.varun.akva.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001e\u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a4\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u00a8\u0006\u000f"}, d2 = {"SectionHeader", "", "title", "", "SettingsScreen", "onBack", "Lkotlin/Function0;", "settingsManager", "Lcom/varun/akva/data/SettingsManager;", "SettingsToggleCard", "description", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "app_debug"})
public final class SettingsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    com.varun.akva.data.SettingsManager settingsManager) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SectionHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SettingsToggleCard(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, boolean checked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onCheckedChange) {
    }
}