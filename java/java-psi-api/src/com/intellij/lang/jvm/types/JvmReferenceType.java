/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.lang.jvm.types;

import com.intellij.lang.jvm.JvmTypeDeclaration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a type which could be resolved into a class or a type parameter and optionally has type arguments.
 * <p>
 * Such type appears in throws, bounds, extends, implements.
 */
public interface JvmReferenceType extends JvmType {

  JvmReferenceType[] EMPTY_ARRAY = new JvmReferenceType[0];

  @NotNull
  String getName();

  @Nullable
  default JvmTypeDeclaration resolve() {
    JvmTypeResolveResult result = resolveType();
    return result == null ? null : result.getDeclaration();
  }

  @Nullable
  JvmTypeResolveResult resolveType();

  @NotNull
  Iterable<JvmType> typeArguments();
}
