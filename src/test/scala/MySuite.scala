import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.networknt.schema.{JsonSchema, JsonSchemaFactory, PathType, SchemaLocation, SchemaValidatorsConfig, SpecVersion}
import com.networknt.schema.JsonSchemaFactory.builder
import com.networknt.schema.SpecVersion.VersionFlag

import java.io.InputStream



// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  }

  test("JsonSchema If else"){
    val factory = JsonSchemaFactory.getInstance(VersionFlag.V202012)

    val schemaPath = "/schema/testSchema.json"
    val dataPath = "/data/testData.json"
    val schemaInputStream = getClass.getResourceAsStream(schemaPath)
    val schema = getJsonSchemaFromStreamContentV7(schemaInputStream)
    val dataInputStream = getClass.getResourceAsStream(dataPath)
    val node = getJsonNodeFromStreamContent(dataInputStream)

    val errors = schema.validate(node)
    println(errors)
    assertEquals(1,1)
  }

  def getJsonSchemaFromStreamContentV7(schemaContent: InputStream): JsonSchema = {
    val factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)
    val config = new SchemaValidatorsConfig()
    config.setFormatAssertionsEnabled(true)
    factory.getSchema(schemaContent,config)
  }


  protected def getJsonNodeFromStreamContent(content:InputStream): JsonNode = {
    val mapper = new ObjectMapper()
    mapper.readTree(content)
  }
}
