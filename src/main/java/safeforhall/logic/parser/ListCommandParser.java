package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import safeforhall.logic.commands.ListCommand;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.person.LastDate;

public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_KEYWORD, CliSyntax.PREFIX_DATE1,
                        CliSyntax.PREFIX_DATE2);
        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_KEYWORD, CliSyntax.PREFIX_DATE1)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String keyword = argMultimap.getValue(CliSyntax.PREFIX_KEYWORD).get();
        if (!isKeywordValid(argMultimap, keyword)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE_LATE));
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_DATE2).isEmpty()) {
            LastDate date = ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE1).get());
            return new ListCommand(keyword, date);
        } else {
            LastDate date1 = ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE1).get());
            LastDate date2 = ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE2).get());
            return new ListCommand(keyword, date1, date2);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isKeywordValid(ArgumentMultimap argumentMultimap, String keyword) {
        boolean normalKeywords = keyword.equals("c") || keyword.equals("f");
        boolean lateKeywords = keyword.equals("lc") || keyword.equals("lf");
        boolean emptyDate2 = argumentMultimap.getValue(CliSyntax.PREFIX_DATE2).isEmpty();
        return normalKeywords || (lateKeywords && emptyDate2);
    }
}
