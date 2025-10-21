## 🧾 **Changelog**

### 🧹 Project Structure & Housekeeping

* **Refactored `.gitignore`** with a full, structured layout:

	* Organized into clear sections (`Gradle`, `Android Studio`, `OS`, `Logs`, etc.).
	* Added proper exceptions for Gradle wrapper files.
	* Ignored sensitive files (keystores, temporary files, caches).
	* Removed redundant `.idea/.gitignore` and IDE metadata files.

* **Cleaned project metadata**:

	* Removed outdated `.idea` configuration files (compiler, misc, vcs, etc.).
	* Updated `.idea/gradle.xml` to use `jbr-21` as the Gradle JVM.
	* Added new code style import layout to `Project.xml` for consistent Kotlin/Java imports.
	* Added Android Vitals settings in `appInsightsSettings.xml`.
	* Removed generated `app/release` artifacts and baseline profiles from version control.

### ⚙️ **Build Configuration**

* **Enhanced `gradle.properties`** for performance and modern build practices:

	* Increased Gradle JVM heap size to **8 GB**.
	* Enabled:

		* **Parallel builds**
		* **Configuration cache**
		* **Gradle build cache**
	* Set `android.enableJetifier=false` (for modern AndroidX-only dependencies).
	* Tuned Kotlin daemon to **4 GB heap**, enabled incremental compilation, IR backend, and caching.
	* Improved output determinism and enabled detailed logging.
	* Switched to rich console output and warning mode `all`.

### 🧩 **Gradle Setup**

* Updated `settings.gradle.kts`:

	* Added `@Suppress("UnstableApiUsage")` annotations for `repositoriesMode`.
	* Included `gradlePluginPortal()` inside `dependencyResolutionManagement`.
	* Enabled `TYPESAFE_PROJECT_ACCESSORS` feature preview for cleaner Gradle accessors.
	* Improved repository configuration structure and readability.

### 🎨 **Code Improvements**

* Minor cleanup in `PostContentVote.kt`:

	* Reordered parameters for better Kotlin style consistency (`modifier` now comes first).