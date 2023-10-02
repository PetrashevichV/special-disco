package by.news.controller;

import java.util.HashMap;
import java.util.Map;

import by.news.controller.impl.AddNews;
import by.news.controller.impl.ChangeLocale;
import by.news.controller.impl.DeleteNews;
import by.news.controller.impl.GoToAddingNewsPage;
import by.news.controller.impl.GoToAuthUserPage;
import by.news.controller.impl.GoToAuthorization;
import by.news.controller.impl.GoToDeletedNewsPage;
import by.news.controller.impl.GoToMainPage;
import by.news.controller.impl.GoToProposedNewsPage;
import by.news.controller.impl.GoToRegistration;
import by.news.controller.impl.GoToUpdatingNewsPage;
import by.news.controller.impl.LogOut;
import by.news.controller.impl.ReadNews;
import by.news.controller.impl.RegistrationNewUser;
import by.news.controller.impl.RestoreDeletedNews;
import by.news.controller.impl.SignIn;
import by.news.controller.impl.UnknownCommand;
import by.news.controller.impl.UpdateNews;
import by.news.controller.impl.ApproveProposedNews;

public class CommandProvider {
	private static final Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new GoToAuthorization());
		commands.put(CommandName.REGISTRATION, new GoToRegistration());
		commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUser());
		commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.LOG_OUT, new LogOut());
		commands.put(CommandName.READ_NEWS, new ReadNews());
		commands.put(CommandName.ADD_NEWS, new AddNews());
		commands.put(CommandName.UPDATE_NEWS, new UpdateNews());
		commands.put(CommandName.DELETE_NEWS, new DeleteNews());
		commands.put(CommandName.APPROVE_PROPOSED_NEWS, new ApproveProposedNews());
		commands.put(CommandName.RESTORE_DELETED_NEWS, new RestoreDeletedNews());
		commands.put(CommandName.GO_TO_AUTH_USER_PAGE, new GoToAuthUserPage());
		commands.put(CommandName.GO_TO_ADDING_NEWS_PAGE, new GoToAddingNewsPage());
		commands.put(CommandName.GO_TO_UPDATING_NEWS_PAGE, new GoToUpdatingNewsPage());
		commands.put(CommandName.GO_TO_PROPOSED_NEWS_PAGE, new GoToProposedNewsPage());
		commands.put(CommandName.GO_TO_DELETED_NEWS_PAGE, new GoToDeletedNewsPage());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
	}

	public Command findCommand(String nameOfCommand) {
		if (nameOfCommand == null) {
			nameOfCommand = CommandName.UNKNOWN_COMMAND.toString();
		}
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(nameOfCommand.toUpperCase());
		} catch (IllegalArgumentException e) {
			// logging
			commandName = CommandName.UNKNOWN_COMMAND;
		}
		return commands.get(commandName);
	}
}
