// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.filePrediction

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.util.registry.Registry
import com.intellij.openapi.vfs.VirtualFile

internal class FilePredictionEditorManagerListener : FileEditorManagerListener {
  override fun selectionChanged(event: FileEditorManagerEvent) {
    val newFile = event.newFile ?: return
    if (shouldRecord()) {
      FilePredictionHandler.getInstance()?.onFileSelected(event.manager.project, newFile, event.oldFile)
    }
  }

  override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
    if (shouldRecord()) {
      FilePredictionHandler.getInstance()?.onFileOpened(source.project, file)
    }
  }

  override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
    if (shouldRecord()) {
      FilePredictionHandler.getInstance()?.onFileClosed(source.project, file)
    }
  }

  private fun shouldRecord() = ApplicationManager.getApplication().isEAP && Registry.get("filePrediction.calculate.features").asBoolean()
}