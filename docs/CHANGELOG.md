# 🧾 Changelog

## [Unreleased]

### ✨ Added

* Introduced **custom vector icons** for post voting:

	* `caret_up.xml`
	* `caret_down.xml`
* Added `docs/CHANGELOG.md` to track future updates.

### 🛠️ Changed

* **SDK and Build Configuration**

	* Upgraded `compileSdk` and `targetSdk` from **35 → 36**.
	* Updated **Gradle Wrapper** from **8.11.1 → 8.13**.
	* Updated **Android Gradle Plugin (AGP)** to **8.13.0**.
	* Bumped Java and Kotlin compatibility to **version 17**.
	* Added `ndkVersion = "27.0.12077973"`.
	* Updated debug `BASE_URL` to `http://10.0.2.2:8080/api/v1`.
	* Enabled `isMinifyEnabled = true` for release builds.
	* Added `applicationIdSuffix = ".debug"` for debug builds.

* **Dependencies**

	* Updated major libraries to the latest stable versions:

		* **Koin** → 4.1.1
		* **Ktor** → 3.3.1
		* **Compose BOM** → 2025.10.00
		* **Lifecycle Runtime KTX** → 2.9.4
		* **Core KTX** → 1.17.0
		* **Activity Compose** → 1.11.0
		* **Navigation Compose** → 2.9.5
		* **Material Components** → 1.13.0
	* Replaced redundant `androidx.navigation.compose` dependency with unified `libs.navigation.compose`.

* **UI Improvements**

	* Replaced default `Icons.Default.KeyboardArrowUp/Down` with new **custom caret vector icons** in `PostContentVote.kt`.
	* Simplified icon rendering by using `ImageVector.vectorResource()`.

### 🧹 Removed

* Removed deprecated `androidx.navigation.compose` dependency.
* Removed unused resource exclusions from the Gradle packaging configuration.