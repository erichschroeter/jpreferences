# JPreferences

Contributors: Erich Schroeter

Java framework for managing preferences for your application.

## Description

The JPreferences project is a Java framework using Swing that manages
preferences for an application. The framework is based off of the
[JFace Preferences][JFacePreferences] package architecture.

This project was started because I could not find an equivalent of the
JFace Preferences package that uses Swing. I had written an application
based on Swing and did not want the [SWT][SWT_Homepage] dependency.

## Usage

Examples of how to use the various classes in the library are provided
in the _examples_ folder.

## Overview

A quick summary of the available objects in the library covering their
purpose.

### Preference Page

> A preference page implements the _IPreferencePage_ interface which
provides methods for handling what the page should do when the **OK**,
**Cancel**, or **Defaults** buttons are clicked. The intention was to
leave a preference page interface open enough to let the developer
decide what the page needs to do for each action. For example, some
pages may not need to actually store any preferences in a preference
store, but instead just display information stored elsewhere in the
application.

### Preference Node

> A preference node implements the _IPreferenceNode_ interface which in
turn implements the _MutatableTreeNode_. The preference node is used
by a preference manager to manage preference pages. A preference node
contains a single preference page which is displayed when the
preference node is selected within a JTree.

### Preference Manager

> A preference manager implements the _IPreferenceManager_ interface
which provides methods for managing preference nodes. The preference
manager holds a reference to the currently displayed preference page
along with a list of listeners (who implement the
_ICurrentPageListener_ interface) to be called when the current page
changes.

### Preference Store

> A preference store implements the _IPreferenceStore_ interface which
provides methods for storing and persisting preferences. The store
interface allows preferences to be stored agnostice to how they are
persisted. There are two (2) preference store implementations that
come with jpreferences.

##### Default Preference Store

 - Stores preferences in a Java properties format.

##### XML Preference Store

 - Stores preferences in an XML format following the _preference.xsd_ schema.

### Preference Dialog

> The preference dialog brings all the components above together to be
presented to the end user.

[JFacePreferences]: http://help.eclipse.org/helios/index.jsp?topic=/org.eclipse.platform.doc.isv/reference/api/org/eclipse/jface/viewers/package-summary.html
[SWT_Homepage]: http://www.eclipse.org/swt/
