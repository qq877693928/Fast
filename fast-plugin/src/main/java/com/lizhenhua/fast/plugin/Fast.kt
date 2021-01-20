package com.lizhenhua.fast.plugin

import com.android.build.gradle.AppExtension
import com.lizhenhua.fast.plugin.core.FastTransformer
import org.gradle.api.Plugin
import org.gradle.api.Project

class Fast : Plugin<Project> {
  override fun apply(project: Project) {
    project.extensions.create("fast", FastExtension::class.java)
    project.extensions.getByType(AppExtension::class.java)
        .registerTransform(FastTransformer(project))
  }
}