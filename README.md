# Thread Names

Thread names is a java library giving the user the ability to annotate methods with `@ThreadName`.

When the method is called the current threads name is changed to include the `toString()` representations
of the method call parameters.  When the method exits the thread name is changed back to the original name.

The only runtime dependency for the library is the eclipse [AspectJ](https://eclipse.org/aspectj) project.

The tests show an example usage from within a spring managed environment.
