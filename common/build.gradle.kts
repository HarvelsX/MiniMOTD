plugins {
  id("net.kyori.blossom")
  id("minimotd.base-conventions")
}

tasks.jar {
  from(rootProject.file("license.txt")) {
    rename { "license_${rootProject.name.lowercase()}.txt" }
  }
}

dependencies {
  api(libs.configurateHocon)
  api(platform(libs.adventureBom))
  api(libs.adventureApi)
  api(libs.adventureTextSerializerPlain)
  api(libs.adventureTextSerializerGson) {
    exclude("com.google.code.gson", "gson")
  }
  api(libs.adventureTextSerializerLegacy)
  api(libs.minimessage)
  compileOnlyApi(libs.slf4jApi)
  compileOnlyApi(libs.checkerQual)
  compileOnlyApi(libs.gson)
  testImplementation(libs.gson)
  compileOnlyApi(libs.guava)
}

blossom {
  val file = "src/main/java/xyz/jpenilla/minimotd/common/Constants.java"
  mapOf(
    "PLUGIN_NAME" to rootProject.name,
    "PLUGIN_VERSION" to project.version.toString(),
    "PLUGIN_WEBSITE" to Constants.GITHUB_URL,
    "GITHUB_USER" to Constants.GITHUB_USER,
    "GITHUB_REPO" to Constants.GITHUB_REPO
  ).forEach { (k, v) ->
    replaceToken("\${$k}", v, file)
  }
}
