//
// OptionsDialog.java
//

/*
LOCI Plugins for ImageJ: a collection of ImageJ plugins including the
Bio-Formats Importer, Bio-Formats Exporter, Bio-Formats Macro Extensions,
Data Browser, Stack Colorizer and Stack Slicer. Copyright (C) 2005-@year@
Melissa Linkert, Curtis Rueden and Christopher Peterson.

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package loci.plugins.prefs;

import ij.gui.GenericDialog;

/**
 * Base class for options dialogs.
 *
 * <dl><dt><b>Source code:</b></dt>
 * <dd><a href="https://skyking.microscopy.wisc.edu/trac/java/browser/trunk/components/loci-plugins/src/loci/plugins/prefs/OptionsDialog.java">Trac</a>,
 * <a href="https://skyking.microscopy.wisc.edu/svn/java/trunk/components/loci-plugins/src/loci/plugins/prefs/OptionsDialog.java">SVN</a></dd></dl>
 */
public abstract class OptionsDialog {

  // -- Constants --

  // status values
  public static final int STATUS_OK       = 0;
  public static final int STATUS_CANCELED = 1;
  public static final int STATUS_FINISHED = 2;

  /** Flag indicating whether to invoke workaround for AWT refresh bug. */
  protected static final boolean IS_GLITCHED =
    System.getProperty("os.name").indexOf("Mac OS X") >= 0;

  // -- Fields --

  /** Options list associated with the dialog. */
  protected OptionsList optionsList;

  // -- Constructor --

  /** Creates a new options dialog with the given associated options. */
  public OptionsDialog(OptionsList optionsList) {
    this.optionsList = optionsList;
  }

  // -- OptionsDialog methods --

  /** Displays the options dialog, returning the status of the operation. */
  public abstract int showDialog();

  // -- Helper methods --

  /**
   * Adds a choice to the given dialog object corresponding
   * to the string option identified by the specified key.
   */
  protected void addChoice(GenericDialog gd, String key) {
    gd.addChoice(optionsList.getLabel(key),
      optionsList.getPossible(key), optionsList.getValue(key));
  }

  /**
   * Adds a checkbox to the given dialog object corresponding
   * to the boolean option identified by the specified key.
   */
  protected void addCheckbox(GenericDialog gd, String key) {
    gd.addCheckbox(optionsList.getLabel(key), optionsList.isSet(key));
  }

  /** Blocks the current thread for the specified number of milliseconds. */
  protected void sleep(long ms) {
    try {
      Thread.sleep(ms);
    }
    catch (InterruptedException exc) { }
  }

}