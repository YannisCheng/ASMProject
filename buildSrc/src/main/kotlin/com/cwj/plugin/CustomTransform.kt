package com.cwj.plugin

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.cwj.plugin.DataConstant.PLUGIN_NAME
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

/**
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/2
 */
class CustomTransform(val project: Project) : Transform() {

    /**
     * 设置插件名称
     */
    override fun getName(): String {
        return PLUGIN_NAME
    }

    /**
     * 设置过滤类型
     */
    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    /**
     * 设置过滤范围
     */
    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    /**
     * 设置是否支持增量编译
     */
    override fun isIncremental(): Boolean {
        return false
    }

    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
        println("--> context is : ${transformInvocation?.context}")
        println("--> referencedInputs is : ${transformInvocation?.referencedInputs}")
        println("--> isIncremental is : ${transformInvocation?.isIncremental}")
        println("--> outputProvider before is : ${transformInvocation?.outputProvider}")
        // 获取inputs
        val inputs = transformInvocation?.inputs
        // 遍历inputs，分2种
        inputs?.forEach { transformInput ->
            println("==> transformInput is : $transformInput")
            run {
                // 目录文件夹是我们的源代码和生成的R文件和BuildConfig文件等
                transformInput.directoryInputs.forEach { directoryInput ->

                    run {
                        println("## directoryInput is : $directoryInput")

                        val dest = transformInvocation.outputProvider.getContentLocation(
                            directoryInput.name + directoryInput.file.absoluteFile.hashCode(),
                            directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY
                        )

                        // 将input的目录复制到output指定目录，
                        // 因为input一定要output，否则就丢失了(transform为链式的)
                        FileUtils.copyDirectory(directoryInput.file, dest)
                    }
                }

                // jar文件一般就是依赖
                transformInput.jarInputs.forEach { jarInput ->
                    run {
                        println("** jarInput is : $jarInput")

                        val dest = transformInvocation.outputProvider.getContentLocation(
                            jarInput.name + jarInput.file.absoluteFile.hashCode(),
                            jarInput.contentTypes, jarInput.scopes, Format.JAR
                        )
                        FileUtils.copyFile(jarInput.file, dest)
                    }
                }
            }
        }
        println("--> outputProvider after is : ${transformInvocation?.outputProvider}")
    }

}