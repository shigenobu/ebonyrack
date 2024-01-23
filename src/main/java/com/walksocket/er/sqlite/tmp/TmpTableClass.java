package com.walksocket.er.sqlite.tmp;

import com.walksocket.er.sqlite.Tmp;
import com.walksocket.er.template.ErTemplateUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * TmpTableClass.
 */
public class TmpTableClass implements Tmp {

  /**
   * filter none.
   */
  public static final String FILTER_NONE = "None";

  /**
   * filter contains.
   */
  public static final String FILTER_CONTAINS = "Contains";

  /**
   * filter start with.
   */
  public static final String FILTER_START_WITH = "Start with";

  /**
   * filter end with.
   */
  public static final String FILTER_END_WITH = "End with";

  /**
   * file name converter ucfirst camel.
   */
  public static final String FILE_NAME_CONVERTER_UCFIRST_CAMEL = "${TableName}";

  /**
   * file name converter ucfirst snake.
   */
  public static final String FILE_NAME_CONVERTER_UCFIRST_SNAKE = "${Table_name}";

  /**
   * file name converter lcfirst camel.
   */
  public static final String FILE_NAME_CONVERTER_LCFIRST_CAMEL = "${tableName}";

  /**
   * file name converter lcfirst snake.
   */
  public static final String FILE_NAME_CONVERTER_LCFIRST_SNAKE = "${table_name}";

  /**
   * file name converter list.
   */
  public static final List<String> FILE_NAME_CONVERTER_LIST = new ArrayList<>();

  static {
    FILE_NAME_CONVERTER_LIST.add(FILE_NAME_CONVERTER_UCFIRST_CAMEL);
    FILE_NAME_CONVERTER_LIST.add(FILE_NAME_CONVERTER_UCFIRST_SNAKE);
    FILE_NAME_CONVERTER_LIST.add(FILE_NAME_CONVERTER_LCFIRST_CAMEL);
    FILE_NAME_CONVERTER_LIST.add(FILE_NAME_CONVERTER_LCFIRST_SNAKE);
  }

  /**
   * template value.
   */
  public String templateValue;

  /**
   * filter table action command.
   */
  public String filterTableActionCommand;

  /**
   * filter table value.
   */
  public String filterTableValue;

  /**
   * convert file prefix value.
   */
  public String convertFilePrefixValue;

  /**
   * convert file name value.
   */
  public String convertFileNameValue;

  /**
   * convert file suffix value.
   */
  public String convertFileSuffixValue;

  /**
   * convert file extension value.
   */
  public String convertFileExtensionValue;

  /**
   * get final file name.
   *
   * @param tableName tableName
   * @return final file name
   */
  public String getFinalFileName(String tableName) {
    var fileName = tableName;
    var converterName = convertFileNameValue.trim();
    if (converterName.equals(FILE_NAME_CONVERTER_UCFIRST_CAMEL)) {
      fileName = ErTemplateUtils.toCamelCase(fileName);
      fileName = ErTemplateUtils.ucFirst(fileName);
    } else if (converterName.equals(FILE_NAME_CONVERTER_UCFIRST_SNAKE)) {
      fileName = ErTemplateUtils.toSnakeCase(fileName);
      fileName = ErTemplateUtils.ucFirst(fileName);
    } else if (converterName.equals(FILE_NAME_CONVERTER_LCFIRST_CAMEL)) {
      fileName = ErTemplateUtils.toCamelCase(fileName);
      fileName = ErTemplateUtils.lcFirst(fileName);
    } else if (converterName.equals(FILE_NAME_CONVERTER_LCFIRST_SNAKE)) {
      fileName = ErTemplateUtils.toSnakeCase(fileName);
      fileName = ErTemplateUtils.lcFirst(fileName);
    }

    return String.format("%s%s%s%s",
        convertFilePrefixValue.trim(),
        fileName,
        convertFileSuffixValue.trim(),
        convertFileExtensionValue.trim());
  }
}
