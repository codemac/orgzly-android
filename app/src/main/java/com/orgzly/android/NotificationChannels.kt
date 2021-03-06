package com.orgzly.android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import com.orgzly.R
import com.orgzly.android.reminders.ReminderService


/**
 * Creates all channels for notifications.
 */
object NotificationChannels {

    @JvmField val ONGOING = "ongoing"
    @JvmField val REMINDERS = "reminders"
    @JvmField val SYNC_PROGRESS = "sync-progress"
    @JvmField val SYNC_FAILED = "sync-failed"


    fun createAll(context: Context) {
        createForOngoing(context)
        createForReminders(context)
        createForSyncProgress(context)
        createForSyncFailed(context)
    }

    private fun createForReminders(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val id = REMINDERS
        val name = context.getString(R.string.reminders_channel_name)
        val description = context.getString(R.string.reminders_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(id, name, importance)

        channel.description = description

        channel.enableLights(true)
        channel.lightColor = Color.BLUE

        channel.vibrationPattern = ReminderService.SCHEDULED_NOTE_VIBRATE_PATTERN

        channel.setShowBadge(false)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(channel)
    }

    private fun createForOngoing(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val id = ONGOING
        val name = context.getString(R.string.ongoing_channel_name)
        val description = context.getString(R.string.ongoing_channel_description)
        val importance = NotificationManager.IMPORTANCE_MIN

        val channel = NotificationChannel(id, name, importance)

        channel.description = description

        channel.setShowBadge(false)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(channel)

        return
    }

    private fun createForSyncProgress(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val id = SYNC_PROGRESS
        val name = context.getString(R.string.sync_progress_channel_name)
        val description = context.getString(R.string.sync_progress_channel_description)
        val importance = NotificationManager.IMPORTANCE_LOW

        val channel = NotificationChannel(id, name, importance)

        channel.description = description

        channel.setShowBadge(false)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(channel)

        return
    }

    private fun createForSyncFailed(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val id = SYNC_FAILED
        val name = context.getString(R.string.sync_failed_channel_name)
        val description = context.getString(R.string.sync_failed_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(id, name, importance)

        channel.description = description

        channel.setShowBadge(true)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(channel)

        return
    }
}