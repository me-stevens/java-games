# Java games

In reality, Java experiments. Never ever use them. Very improvable ;-)

## Trivial

Uses the pre-Swing libraries (Sun's AWT, not SWT). They are going to die soon, so I might revisit this game in the future if I decide to start learning JavaFX, which is replacing Swing.

The ranking is just a "top ten".

To do:

- [ ] Fix drawString painting new things over the old ones. Use a grey background? Then I have to use labels.
- [ ] Handle the case when the `Questions` functions return `"Reset"`.
- [ ] Improve look and feel, paddings, font, text size, etc.

### MadMax

As Trivial, this is a work in progress. It is also a console application.

To do:

- [ ] Finish tests
- [ ] Refactor the Gang class
- [ ] Probably refactor the MadMax class
- [ ] Move all the console messages to the printer class?
- [ ] Add more interactivity

## Compiling and running

### trivial

On a terminal, type:

```bash
$ javac trivial/Launcher.java
```

and if there aren't any errors, run with:

```bash
$ java trivial.Launcher
```

A simple window should pop out.

### MadMax

Open the project in NetBeans and click the Run button or press F6.

## Dedicated site

link

## License

[![License](https://img.shields.io/badge/gnu-license-green.svg?style=flat)](https://opensource.org/licenses/GPL-2.0)
GNU License
