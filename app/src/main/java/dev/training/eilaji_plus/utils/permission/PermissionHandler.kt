package dev.training.eilaji_plus.utils.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat


class PermissionHandler(private val context: Context) {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var permissionCallback: PermissionCallback


    // Set the requestPermissionLauncher in onCreate, onAttach, or onViewCreated
    fun setRequestPermissionLauncher(requestPermissionLauncher: ActivityResultLauncher<Array<String>>) {
        this.requestPermissionLauncher = requestPermissionLauncher
    }

    // Set the permission callback
    fun setPermissionCallback(callback: PermissionCallback) {
        permissionCallback = callback
    }

    // Check if all permissions are granted
    private fun arePermissionsGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    // Launch the Permission runtime Dialog for the user
    fun requestPermissions() {
        if (::requestPermissionLauncher.isInitialized) {
            val permissionsArray = REQUIRED_PERMISSIONS.toTypedArray()
            requestPermissionLauncher.launch(permissionsArray)
        } else {
            // Handle the case when requestPermissionLauncher is not set
            // Log an error or throw an exception, or handle it accordingly
            throw IllegalStateException("Request permission launcher is not set. Make sure to call setRequestPermissionLauncher() before requesting permissions.")
        }
    }

    // Check if a specific permission is in the set of required permissions
    fun hasPermission(permission: String): Boolean {
        return REQUIRED_PERMISSIONS.contains(permission)
    }

    // Get the set of required permissions
    fun getRequiredPermissions(): Set<String> {
        return REQUIRED_PERMISSIONS.toSet()
    }

    // Example scenario where the callback is used
    fun performActionRequiringPermissions() {
        if (permissionCallback == null) {
            throw NullPointerException("Permission callback is not set. Make sure to call setPermissionCallback() before performing the action requiring permissions.")
        }
        if (arePermissionsGranted()) {
            // Perform the action that requires permissions
            permissionCallback.onPermissionsGranted()
        } else {
            // Handle the case when permissions are not granted
            permissionCallback.onPermissionsDenied()
        }
    }


    companion object {
        private val REQUIRED_PERMISSIONS = mutableSetOf(Manifest.permission.INTERNET)
    }

    /**
     * This is how you can initialize the requestPermissionLauncher
     *
     * requestPermissionLauncher =
     * registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
     *
     *     // Check if all permissions are granted
     *     val allGranted = permissions.all { it.value }
     *
     *     if (allGranted) {
     *         // All permissions granted, you can proceed with sending notifications
     *         Toast.makeText(requireContext(),"Great! Now you are all set to use The Reminder",
     *             Toast.LENGTH_LONG).show()
     *     } else {
     *         // Permission denied, handle accordingly
     *         // At least one permission denied, handle accordingly (e.g., show a message or disable certain features)
     *         Toast.makeText(requireContext(),
     *             "Permission denied. Cannot create reminder.", Toast.LENGTH_SHORT).show()
     *     }
     * }
     * // Set its value to the class to make it valid. This is important.
     * permissionHandler.setRequestPermissionLauncher(requestPermissionLauncher)
     */

    /**
     * Example of usage:
     *
     * fun getUserLastLocation() {
     *     if (arePermissionsGranted()) {
     *         val locationController = LocationController(AppController.getInstance())
     *         val location = locationController.getUserLocationLatLng()
     *         if (location != null) {
     *             val latitude = location.latitude
     *             val longitude = location.longitude
     *             // Use the latitude and longitude values
     *             _currentLocation.value = location
     *             Log.i("MVM", "updateLastLocation: location: $location, lat: $latitude, lng: $longitude")
     *         } else {
     *             // Location retrieval failed
     *             fusedLocationClient.lastLocation.addOnSuccessListener { fusedLocationClientLocation ->
     *                 fusedLocationClientLocation?.let {
     *                     val currentLatLng = LatLng(it.latitude, it.longitude)
     *                     _currentLocation.value = currentLatLng
     *                     Log.i("MVM", "FusedUpdateLastLocation: location: $it, lat: ${it.latitude}, lng: ${it.longitude}")
     *                 }
     *             }.addOnFailureListener { println("addOnFailureListener " + it.message) }
     *         }
     *     } else {
     *         // Handle the case when location permission is not granted
     *         requestPermissions()
     *     }
     * }
     */

    //********************************
    fun addRuntimePermission(permission: String) {
        REQUIRED_PERMISSIONS.add(permission)
    }

    fun addRuntimePermission(permission: Set<String>) {
        REQUIRED_PERMISSIONS.addAll(permission)
    }

    fun removeRuntimePermission(permission: String) {
        REQUIRED_PERMISSIONS.remove(permission)
    }

    fun removeRuntimePermission(permission: Set<String>) {
        REQUIRED_PERMISSIONS.removeAll(permission)
    }


    // Permission callback interface
    interface PermissionCallback {
        fun onPermissionsGranted()
        fun onPermissionsDenied()
    }

    /**
     *
     * * class MainActivity : AppCompatActivity(), PermissionHandler.PermissionCallback {
     *
     * private late-init var permissionHandler: PermissionHandler
     *
     * override fun onCreate(savedInstanceState: Bundle?) {
     *
     * super.onCreate(savedInstanceState)
     *
     * setContentView(R.layout.activity_main)
     *
     * // Create an instance of PermissionHandler
     *
     * permissionHandler = PermissionHandler(this)
     *
     * // Set the requestPermissionLauncher and permission callback
     *
     * permissionHandler.setRequestPermissionLauncher(registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
     *
     * val allGranted = permissions.all { it.value }
     *
     * if (allGranted) {
     *
     * onPermissionsGranted()
     *
     * } else {
     *
     * onPermissionsDenied()
     *
     * }
     *
     * })
     *
     * permissionHandler.setPermissionCallback(this)
     *
     * // Check permissions and request if needed
     *
     * if (!permissionHandler.arePermissionsGranted()) {
     *
     * permissionHandler.requestPermissions()
     *
     * }
     *
     * }
     *
     * override fun onPermissionsGranted() {
     *
     * // Handle the case when all permissions are granted
     *
     * Toast.makeText(this, "Permissions granted!", Toast.LENGTH_SHORT).show()
     *
     * // Continue with your app logic
     *
     * }
     *
     * override fun onPermissionsDenied() {
     *
     * // Handle the case when any permission is denied
     *
     * Toast.makeText(this, "Permissions denied!", Toast.LENGTH_SHORT).show()
     *
     * // Handle the denied permission scenario or disable certain features
     *
     * }
     *
     * // Rest of your MainActivity code...
     *
     * }
     */
}
