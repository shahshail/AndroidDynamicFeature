package app.shahshail.com.dynamicfeature

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory


private const val packageName = "com.google.android.samples.dynamicfeatures.ondemand"

private const val instantPackageName = "com.google.android.samples.instantdynamicfeatures"

private const val kotlinSampleClassname = "$packageName.KotlinSampleActivity"

private const val javaSampleClassname = "$packageName.JavaSampleActivity"

private const val nativeSampleClassname = "$packageName.NativeSampleActivity"

private const val instantSampleClassname = "$instantPackageName.SplitInstallInstantActivity"

/** Activity that displays buttons and handles loading of feature modules. */
class MainActivity : AppCompatActivity() {

//    /** Listener used to handle changes in state for install requests. */
//    private val listener = SplitInstallStateUpdatedListener { state ->
//        val multiInstall = state.moduleNames().size > 1
//        state.moduleNames().forEach { name ->
//            // Handle changes in state.
//            when (state.status()) {
//                SplitInstallSessionStatus.DOWNLOADING -> {
//                    //  In order to see this, the application has to be uploaded to the Play Store.
//                    displayLoadingState(state, "Downloading $name")
//                }
//                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
//                    /*
//                      This may occur when attempting to download a sufficiently large module.
//                      In order to see this, the application has to be uploaded to the Play Store.
//                      Then features can be requested until the confirmation path is triggered.
//                     */
//                    startIntentSender(state.resolutionIntent()?.intentSender, null, 0, 0, 0)
//                }
//                SplitInstallSessionStatus.INSTALLED -> {
//                    onSuccessfulLoad(name, launch = !multiInstall)
//                }
//
//                SplitInstallSessionStatus.INSTALLING -> displayLoadingState(state, "Installing $name")
//                SplitInstallSessionStatus.FAILED -> {
//                    toastAndLog("Error: ${state.errorCode()} for module ${state.moduleNames()}")
//                }
//            }
//        }
//    }
//
//    private val moduleKotlin// by lazy { getString(R.string.module_feature_kotlin) }
//    private val moduleJava //by lazy { getString(R.string.module_feature_java) }

    private val clickListener by lazy {
        View.OnClickListener {
            when (it.id) {
                //R.id.btn_load_kotlin -> loadAndLaunchModule(moduleKotlin)
                //R.id.btn_load_java -> loadAndLaunchModule(moduleJava)

            }
        }
    }

    private lateinit var manager: SplitInstallManager

    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = SplitInstallManagerFactory.create(this)
    }

//    override fun onResume() {
//        // Listener can be registered even without directly triggering a download.
//        manager.registerListener(listener)
//        super.onResume()
//    }
//
//    override fun onPause() {
//        // Make sure to dispose of the listener once it's no longer needed.
//        manager.unregisterListener(listener)
//        super.onPause()
//    }

//    /**
//     * Load a feature by module name.
//     * @param name The name of the feature module to load.
//     */
//    private fun loadAndLaunchModule(name: String) {
//        updateProgressMessage("Loading module $name")
//        // Skip loading if the module already is installed. Perform success action directly.
//        if (manager.installedModules.contains(name)) {
//            updateProgressMessage("Already installed")
//            onSuccessfulLoad(name, launch = true)
//            return
//        }
//
//        // Create request to install a feature module by name.
//        val request = SplitInstallRequest.newBuilder()
//            .addModule(name)
//            .build()
//
//        // Load and install the requested feature module.
//        manager.startInstall(request)
//
//        updateProgressMessage("Starting install for $name")
//    }

    private fun openUrl(url: String) {

        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage(packageName)
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        startActivity(intent)
    }

    /** Install all features but do not launch any of them. */
//    private fun installAllFeaturesNow() {
//        // Request all known modules to be downloaded in a single session.
//        val request = SplitInstallRequest.newBuilder()
//            .addModule(moduleKotlin)
//            .addModule(moduleJava)
//            .build()
//
//        // Start the install with above request.
//        manager.startInstall(request).addOnSuccessListener {
//            toastAndLog("Loading ${request.moduleNames}")
//        }
//    }

    /** Request uninstall of all features. */
    private fun requestUninstall() {

        toastAndLog("Requesting uninstall of all modules." +
                "This will happen at some point in the future.")

        val installedModules = manager.installedModules.toList()
        manager.deferredUninstall(installedModules).addOnSuccessListener {
            toastAndLog("Uninstalling $installedModules")
        }
    }
//
//    /**
//     * Define what to do once a feature module is loaded successfully.
//     * @param moduleName The name of the successfully loaded module.
//     * @param launch `true` if the feature module should be launched, else `false`.
//     */
//    private fun onSuccessfulLoad(moduleName: String, launch: Boolean) {
//        if (launch) {
//            when (moduleName) {
//                moduleKotlin -> launchActivity(kotlinSampleClassname)
//                moduleJava -> launchActivity(javaSampleClassname)
//            }
//        }
//
//        displayButtons()
//    }
//
//    /** Launch an activity by its class name. */
//    private fun launchActivity(className: String) {
//        Intent().setClassName(packageName, className)
//            .also {
//                startActivity(it)
//            }
//    }
//
//    /** Display a loading state to the user. */
//    private fun displayLoadingState(state: SplitInstallSessionState, message: String) {
//        displayProgress()
//
//        progressBar.max = state.totalBytesToDownload().toInt()
//        progressBar.progress = state.bytesDownloaded().toInt()
//
//        updateProgressMessage(message)
//    }
//

    private fun setClickListener(id: Int, listener: View.OnClickListener) {
        findViewById<View>(id).setOnClickListener(listener)
    }

//    private fun updateProgressMessage(message: String) {
//        if (progress.visibility != View.VISIBLE) displayProgress()
//        progressText.text = message
//    }
//
//    /** Display progress bar and text. */
//    private fun displayProgress() {
//        progress.visibility = View.VISIBLE
//        buttons.visibility = View.GONE
//    }
//
//    /** Display buttons to accept user input. */
//    private fun displayButtons() {
//        progress.visibility = View.GONE
//        buttons.visibility = View.VISIBLE
//    }

}

fun MainActivity.toastAndLog(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    Log.d(TAG, text)
}

private const val TAG = "DynamicFeatures"