/*
 * Copyright (C) 2000 - 2014 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception. You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/docs/core/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.silverpeas.util.file;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileUtilTest {

  @Test
  public void testDeleteEmptyDir() throws IOException {
    File root = File.createTempFile("prefix", "suffix");
    FileUtils.deleteQuietly(root);
    assertThat(root.exists(), is(false));

    File newFile = new File(root, "aFile.txt");
    FileUtils.touch(newFile);
    assertThat(root.exists(), is(true));
    assertThat(root.isDirectory(), is(true));
    assertThat(newFile.exists(), is(true));
    assertThat(newFile.isFile(), is(true));

    assertThat(FileUtil.deleteEmptyDir(root), is(false));
    assertThat(root.exists(), is(true));
    assertThat(root.isDirectory(), is(true));
    assertThat(newFile.exists(), is(true));
    assertThat(newFile.isFile(), is(true));

    assertThat(newFile.delete(), is(true));
    assertThat(newFile.exists(), is(false));

    assertThat(FileUtil.deleteEmptyDir(root), is(true));
    assertThat(root.exists(), is(false));
  }
}