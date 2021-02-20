package com.cwj.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor

/**
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/2
 */
class CustomPlugin : Plugin<Project> {


    override fun apply(project: Project) {
        if (project.plugins.hasPlugin(AppPlugin::class.java)) {

            val extension = project.extensions.create("customExtensions", CustomExtensions::class.java)
            val androidExtension: AppExtension = project.extensions.getByType(AppExtension::class.java)
            println("====> extension param is ${extension.SWITCH_PLUGIN_OPEN}")
            if (extension.SWITCH_PLUGIN_OPEN) {
                println("====> plugin is begin, kaixina ====>")
                androidExtension.registerTransform(CustomTransform(project))
            } else {
                println("====> plugin not begin ====>")
            }
        }

    }
}