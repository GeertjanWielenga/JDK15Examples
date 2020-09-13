package com.vip.jfeatures.jdk15.textblocks;

public class TextBlocksDemo {
    public static void main(String[] args) {
        String myFirstTextBlock = """
                Hello this is first text block.
                """;
        System.out.println(myFirstTextBlock);
        System.out.println(printSimpleTextBlock());
        tryTextBlock();
        plainOldMultiLineString();
        printHTML14SpacesInFront();
        printHTML14SpacesInFrontAndTrailingSpaces();
        printHTML14SpacesInFrontOnlyOneLineMovedToLeft();
        printHTML14SpacesInFrontOnlyLastDelimiterLineMovedToLeft();
        printHTML14SpacesInFrontOnlyLastDelimiterLineMovedToRight();
        printTextBlock();
        printInitialCharacterPositionDecidedByLeftMostCharacterOfLines();
        printInitialCharacterPositionDecidedByEndDelimiter();
        printTextBlockMovingEndDelimiterToRightOfContentHasNoEffect();
        printEscapeProcessing();
        System.out.println("End");
    }

    /**
     * Escape processing:
     *
     * We have seen first line terminators are interpreted, then next step is white space removal and at the end
     * escape processing. When we write \n , it will not be modified by initial 2 steps and will be interpreted
     * at end.
     *
     * In this example few things to observe are:
     * 1. \n is allowed in text block and it generates additional new line in output.
     * 2. " and ' can be used freely in text blocks like Strings.
     * 3. to use \ we need to use \\.
     * 4. Sequences of three " characters require the escaping of at least one " to avoid mimicking the closing delimiter.
     * 5. To allow finer control of the processing of newlines and white space, two new escape sequences are
     * introduced in java 15.
     *    a.    ***\*** at the end acts like concatenation of 2 Strings, in other words it avoids line terminator between
     *    consecutive lines.
     *    b.    ***\s*** adds space
     */
    private static void printEscapeProcessing() {
        String textBlock = """
                "Hello\n
                Text Block " ' \\ \t "
                experiment another opening/closing delimiter type of 3 consecutive quotes \"""
                without newline concatenation of Strings \
                spaces \s\sat end in this way trailing spaces are not removed\s\s
                \"""";

        System.out.println("printEscapeProcessing");
        System.out.println(textBlock);
    }

    public static void printTextBlock() {
        String textBlock = """
                First line of test block
                Second line of test block
                """;
        System.out.println("textBlock Experiment printTextBlock");
        System.out.println(textBlock);
    }


    /**
     * There has to be one line terminator immediately after initial opening delimiter.
     * Now remaining is content and closing delimiter. if we move any line of content or closing delimiter to left
     * it reduces common whitespace prefix. In other words starting character of all lines in a text block is decided
     * on basis of left most character in content or end delimiter.
     */
    public static void printInitialCharacterPositionDecidedByLeftMostCharacterOfLines() {
        String textBlock = """
                First line of test block
            Second line of test block
                """;
        System.out.println("textBlock Experiment printInitialCharacterPositionDecidedByLeftMostCharacterOfLines");
        System.out.println(textBlock);
    }

    public static void printInitialCharacterPositionDecidedByEndDelimiter() {
        String textBlock = """
                First line of test block         
                Second line of test block      
            """;
        System.out.println("textBlock Experiment printInitialCharacterPositionDecidedByEndDelimiter");
        System.out.println(textBlock);
    }

    public static void printTextBlockMovingEndDelimiterToRightOfContentHasNoEffect()
    {
        String textBlock = """
                First line of test block         
                Second line of test block      
                    """;
        System.out.println("textBlock Experiment printTextBlockMovingEndDelimiterToRightOfContentHasNoEffect");
        System.out.println(textBlock);
    }

    /**
     * The common white space prefix is 14, so 14 white spaces are removed from the start of each line.
     * The trailing blank line is stripped to leave an empty line, which being the last line is then discarded.
     * Moving the closing delimiter to the right of the content has no effect
     */
    private static void printHTML14SpacesInFrontOnlyLastDelimiterLineMovedToRight() {
        String textBlockWith14SpacesInFrontOnlyLastDelimiterLineMovedToRight ="""
                                                                             <html>
                                                                                <body>
                                                                                    <p>Hello, world</p>          
                                                                                </body>
                                                                             </html>
                                                                                 """;
        System.out.println("Print printHTML14SpacesInFrontOnlyLastDelimiterLineMovedToRight in front");
        System.out.println(textBlockWith14SpacesInFrontOnlyLastDelimiterLineMovedToRight);
    }

    /**
     * Since last delimiter is moved 4 spaces left, added 4 spaces in all other lines
     */
    private static void printHTML14SpacesInFrontOnlyLastDelimiterLineMovedToLeft() {
        String textBlockWith14SpacesInFrontOnlyLastDelimiterLineMovedToLeft ="""
                                                                             <html>
                                                                                <body>
                                                                                    <p>Hello, world</p>          
                                                                                </body>
                                                                             </html>
                                                                         """;
        System.out.println("Print printHTML14SpacesInFrontOnlyLastDelimiterLineMovedToLeft in front");
        System.out.println(textBlockWith14SpacesInFrontOnlyLastDelimiterLineMovedToLeft);
    }

    /**
     * Since one line is moved 4 spaces left, added 4 spaces in all other lines
     */
    private static void printHTML14SpacesInFrontOnlyOneLineMovedToLeft() {
        String textBlockWith14SpacesInFrontOnlyOneLineMovedToLeft ="""
                                                                   <html>
                                                                      <body>
                                                                          <p>Hello, world</p>          
                                                                     </body> 
                                                               </html>         
                                                                   """;
        System.out.println("Print textBlockWith14SpacesInFrontOnlyOneLineMovedToLeft in front");
        System.out.println(textBlockWith14SpacesInFrontOnlyOneLineMovedToLeft);
    }

    private static void printHTML14SpacesInFrontAndTrailingSpaces() {
        String textBlockWith14SpacesInFrontAndTrailingSpaces ="""
                                                              <html>   
                                                                  <body>
                                                                      <p>Hello, world</p>          
                                                                  </body> 
                                                              </html>         
                                                              """;
        System.out.println("Print textBlockWith14SpacesInFrontAndTrailingSpaces in front");
        System.out.println(textBlockWith14SpacesInFrontAndTrailingSpaces);
    }

    private static void printHTML14SpacesInFront() {
        String textBlockWith14Spaces ="""
                                      <html>
                                          <body>
                                              <p>Hello, world</p>
                                          </body>
                                      </html>
                                      """;
        System.out.println("Print textBlockWith14Spaces in front for all rows");
        System.out.println(textBlockWith14Spaces);
    }

    private static String printSimpleTextBlock() {
        return """
                             First line
                        This is simple text block
                \t\tLine with tabs""";
    }

    private static void tryTextBlock() {
        String tryTextBlock = """
                     First line
                This is simple text block
                Line with tabs""";
        System.out.println(tryTextBlock);
    }

    private static void plainOldMultiLineString() {
        String oldString = " Hello " +
                "This is multiline " +
                "String";
        String oldStringSQLExample = "select emp_id, emp_name, emp_num_of_kids, emp_active" +
                "from employee_table" +
                "where employee_num_of_kids =1 ";

        System.out.println(oldStringSQLExample);

        String sqlWithTextBlocks = """
                select emp_id, emp_name, emp_num_of_kids, emp_active
                from employee_table
                where employee_num_of_kids =1 
                """;

        System.out.println(sqlWithTextBlocks);
    }

}
