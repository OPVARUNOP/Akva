package com.varun.akva.services

import android.view.accessibility.AccessibilityNodeInfo
import com.varun.akva.services.AKVAAccessibilityService
import kotlinx.coroutines.delay

class AKVAAutomationService {

    fun performClick(text: String): Boolean {
        val root = AKVAAccessibilityService.instance?.rootInActiveWindow ?: return false
        val nodes = root.findAccessibilityNodeInfosByText(text)
        if (nodes.isNullOrEmpty()) return false
        
        for (node in nodes) {
            if (node.isClickable) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                return true
            } else {
                var parent = node.parent
                while (parent != null) {
                    if (parent.isClickable) {
                        parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        return true
                    }
                    parent = parent.parent
                }
            }
        }
        return false
    }

    suspend fun inputText(text: String): Boolean {
        val root = AKVAAccessibilityService.instance?.rootInActiveWindow ?: return false
        val focusNode = root.findFocus(AccessibilityNodeInfo.FOCUS_INPUT) ?: return false
        
        val args = android.os.Bundle()
        args.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text)
        focusNode.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args)
        return true
    }

    fun scroll(direction: String): Boolean {
        val root = AKVAAccessibilityService.instance?.rootInActiveWindow ?: return false
        val action = if (direction.uppercase() == "DOWN") 
            AccessibilityNodeInfo.ACTION_SCROLL_FORWARD 
        else 
            AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD
            
        return traverseAndScroll(root, action)
    }

    private fun traverseAndScroll(node: AccessibilityNodeInfo, action: Int): Boolean {
        if (node.isScrollable) {
            return node.performAction(action)
        }
        for (i in 0 until node.childCount) {
            val child = node.getChild(i)
            if (child != null && traverseAndScroll(child, action)) return true
        }
        return false
    }
}
