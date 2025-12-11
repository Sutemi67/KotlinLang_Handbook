package apc.appcradle.notifications

/**
 * ### MainActivity
 * ```
 * private val notificationPermissionLauncher = registerForActivityResult(
 *         ActivityResultContracts.RequestPermission()
 *     ) { granted ->
 *         if (granted) {
 *             // Разрешение получено, можно показывать уведомления
 *         } else {
 *             // Разрешение отклонено
 *         }
 *     }
 * ```
 *
 * ### onCreate
 * ```
 *     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
 *             notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
 *         }
 * ```
 *
 * ### Использование
 * ```
 * ElevatedButton(onClick = {
 *  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
 *      if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
 *          notificationService.showNotification(Counter.value)
 *      }
 *  } else {
 *      notificationService.showNotification(Counter.value)
 *      }
 *  }
 * ```
 */
object PermissionsAnswer

/**
 * ```
 * class CounterNotificationService(
 *     private val context: Context,
 * ) {
 *     private val notificationManager =
 *         context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
 *
 *     init {
 *         createNotificationChannel()
 *     }
 *
 *     private fun createNotificationChannel() {
 *         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
 *             val channel = NotificationChannel(
 *                 COUNTER_CHANNEL_ID,
 *                 "Counter",
 *                 NotificationManager.IMPORTANCE_DEFAULT
 *
 *             )
 *             channel.description = "Used for increment notifications"
 *
 *             notificationManager.createNotificationChannel(channel)
 *             logger("channel created for android api ${Build.VERSION.SDK_INT}, ${Build.VERSION.CODENAME}")
 *         }
 *         logger("channel created")
 *     }
 * ```
 */
object ChannelAnswer

/**
 * ```
 * fun showNotification(counter: Int) {
 *         logger("start creating notification")
 *         val intent = Intent(context, MainActivity::class.java)
 *
 *         val pendingIntent = PendingIntent.getActivity(
 *             context,
 *             1,
 *             intent,
 *             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
 *         )
 *         val incrementIntent = PendingIntent.getBroadcast(
 *             context,
 *             2,
 *             Intent(context, NotificationReceiver::class.java),
 *             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
 *
 *         )
 *         val notification = NotificationCompat.Builder(
 *             context, COUNTER_CHANNEL_ID
 *         )
 *             .setSmallIcon(R.drawable.ic_launcher_foreground)
 *             .setContentTitle("Increment counter")
 *             .setContentText("Count is $counter")
 *             .setContentIntent(pendingIntent)
 *             .addAction(R.drawable.ic_launcher_foreground, "increment", incrementIntent)
 *             .build()
 *         notificationManager.notify(
 *             1,
 *             notification
 *         )
 *         logger("end of creating notification")
 *
 *     }
 * ```
 */
object NotificationShow