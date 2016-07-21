package org.jugbd.mnet.utils;

import org.jugbd.mnet.domain.Investigation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Bazlur Rahman Rokon
 * @since 7/22/16.
 */
public class FormGenerator {

	public static void main(String[] args) {
		Field[] fields = Investigation.class.getDeclaredFields();
		StringBuilder builder = new StringBuilder();
		String[] ignoredFields = {"id", "version", "createdDate", "lastModifiedDate", "createdBy", "lastModifiedBy", "register"};

		final int[] fieldCount = {0};

		Arrays.asList(fields).stream().forEach(field -> {
			boolean anyMatch = Arrays.asList(ignoredFields).stream().anyMatch(s -> s.equalsIgnoreCase(field.getName()));

			if (!anyMatch
				&& !field.getType().isEnum()
				&& !field.getType().isAssignableFrom(String.class)
				&& !field.getType().isAssignableFrom(Date.class)
				) {

				Class<?> fieldType = field.getType();
				Field[] declaredFields = fieldType.getDeclaredFields();

				if (declaredFields.length > 0) {
					Arrays.asList(declaredFields).stream().forEach(f -> {
						String printField = printField(f, field);
						builder.append(printField);

						fieldCount[0]++;
					});
				}
			} else {
				String printField = printField(field);
				builder.append(printField);
				fieldCount[0]++;
			}
		});

		System.out.println("total field: " + fieldCount[0]);
		write(builder);
	}

	private static void write(StringBuilder builder) {
		try {
			File file = new File("create.html");
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();

			String s = builder.toString();
			byte[] bytes = s.getBytes();

			Files.write(Paths.get(file.getPath()), bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String printField(Field field, Field parentField) {
		String parentFieldName = parentField.getName();
		String fieldName = field.getName();
		String name = Arrays.asList(org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(fieldName))
			.stream()
			.map(org.apache.commons.lang3.StringUtils::capitalize)
			.collect(Collectors.joining(" "));

		if (field.getType().isAssignableFrom(Date.class)) {
			return "                                    <div class=\"form-group\">\n" +
				"                                        <label class=\"col-sm-2 control-label required-field\"> " + org.apache.commons.lang3.StringUtils.capitalize(name) + " </label>\n" +
				"\n" +
				"                                        <div class=\"col-sm-10\">\n" +
				"                                            <input type=\"text\" class=\"form-control date\"\n" +
				"                                                   th:field=\"*{" + parentFieldName + "." + fieldName + "}\"/>\n" +
				"\n" +
				"                                            <p class=\"text-danger\"\n" +
				"                                               th:if=\"${#fields.hasErrors('" + parentFieldName + "." + fieldName + "')}\">\n" +
				"                                                <span th:errors=\"*{" + parentFieldName + "." + fieldName + "}\">Required</span>\n" +
				"                                            </p>\n" +
				"                                        </div>\n" +
				"                                    </div>" +
				"\n" +
				"\n";

		}

		return "                                    <div class=\"form-group\">\n" +
			"                                        <label class=\"col-sm-2 control-label required-field\"> " + org.apache.commons.lang3.StringUtils.capitalize(name) + " </label>\n" +
			"\n" +
			"                                        <div class=\"col-sm-10\">\n" +
			"                                            <input type=\"text\" class=\"form-control\"\n" +
			"                                                   th:field=\"*{" + parentFieldName + "." + fieldName + "}\"/>\n" +
			"\n" +
			"                                            <p class=\"text-danger\"\n" +
			"                                               th:if=\"${#fields.hasErrors('" + parentFieldName + "." + fieldName + "')}\">\n" +
			"                                                <span th:errors=\"*{" + parentFieldName + "." + fieldName + "}\">Required</span>\n" +
			"                                            </p>\n" +
			"                                        </div>\n" +
			"                                    </div>" +
			"\n" +
			"\n";
	}

	private static String printField(Field field) {
		String fieldName = field.getName();
		String name = Arrays.asList(org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(fieldName))
			.stream()
			.map(org.apache.commons.lang3.StringUtils::capitalize)
			.collect(Collectors.joining(" "));

		if (field.getType().isAssignableFrom(Date.class)) {

			return "                                    <div class=\"form-group\">\n" +
				"                                        <label class=\"col-sm-2 control-label required-field\"> " + org.apache.commons.lang3.StringUtils.capitalize(name) + " </label>\n" +
				"\n" +
				"                                        <div class=\"col-sm-10\">\n" +
				"                                            <input type=\"text\" class=\"form-control date\"\n" +
				"                                                   th:field=\"*{" + fieldName + "}\"/>\n" +
				"\n" +
				"                                            <p class=\"text-danger\"\n" +
				"                                               th:if=\"${#fields.hasErrors('" + fieldName + "')}\">\n" +
				"                                                <span th:errors=\"*{" + fieldName + "}\">Required</span>\n" +
				"                                            </p>\n" +
				"                                        </div>\n" +
				"                                    </div>" +
				"\n" +
				"\n";
		}

		return "                                    <div class=\"form-group\">\n" +
			"                                        <label class=\"col-sm-2 control-label required-field\"> " + org.apache.commons.lang3.StringUtils.capitalize(name) + " </label>\n" +
			"\n" +
			"                                        <div class=\"col-sm-10\">\n" +
			"                                            <input type=\"text\" class=\"form-control\"\n" +
			"                                                   th:field=\"*{" + fieldName + "}\"/>\n" +
			"\n" +
			"                                            <p class=\"text-danger\"\n" +
			"                                               th:if=\"${#fields.hasErrors('" + fieldName + "')}\">\n" +
			"                                                <span th:errors=\"*{" + fieldName + "}\">Required</span>\n" +
			"                                            </p>\n" +
			"                                        </div>\n" +
			"                                    </div>" +
			"\n" +
			"\n";
	}
}
