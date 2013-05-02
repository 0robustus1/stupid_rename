# stupid_rename

At many german universities the Stud.IP tool is used to
manage courses. If you download provided files for a course (slides
and other documents) in bulk Stud.IP will rename every file
by prepending a `[number]_` to the filename before
putting them into the downloadable *.zip*-file. 

## Installation

Just download the jar-file.

## Usage

    $ java -jar stupid_rename-0.1.2-standalone.jar [files] [directories]

or

    $ stupid_rename [files] [directories]

### GUI-Mode

If run without any options, *stupid_rename* will start in
GUI-Mode. This will supply a swing-based GUI (by using seesaw)
which will open a window displaying all zip archives in
`~/Downloads` and allowing you to either unzip
the renamed files to the `~/Downloads` directory
or to supply your own directoy to unzip to.

### Options

- `-n` or `--dryrun`
  performs all actions in dryrun-mode which will print
  the current filename and the converted filename to STDOUT.

## Examples

    $ stupid_rename -n /path/to/directory/

Will print all conversions to STDOUT. If you really wish to perform
them, omit the `-n` switch. 

    $ stupid_rename /path/to/directory/ /path/to/file somefile

This will perform renaming-actions on the given files, and on all
first-level files inside of the directory. First-level means
the files, which would be printed by using a simple `ls` or
`dir` call.

## Development information

### Bugs

Filepath interpolation could be tricky, although
i didn't have problems so far. If some occur (can be tested
via the `-n` switch), please contact me.

### Planned Features

- Drag-And-Drop GUI mode...
- unzip from commandline...

## License

Copyright © 2013 Tim Reddehase

Distributed under MIT license.
