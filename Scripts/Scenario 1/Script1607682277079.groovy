import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import java.util.List as List
import java.util.concurrent.locks.Condition as Condition
import java.util.ArrayList as ArrayList

response = WS.sendRequest(findTestObject('User/Route 3 - Create New User'))

WS.verifyResponseStatusCode(response, 201)

WS.verifyElementPropertyValue(response, 'name', 'morpheus')

WS.verifyElementPropertyValue(response, 'job', 'leader')

def slurper = new groovy.json.JsonSlurper()

def result = slurper.parseText(response.getResponseBodyContent())

def value = result.id

GlobalVariable.newId = value

println(GlobalVariable.newId)

String body = '{\n"data": {\n"id": 2,\n"email": "janet.weaver@reqres.in",\n"first_name": "Janet",\n"last_name": "Weaver",\n"avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"\n},\n"ad": {\n"company": "StatusCode Weekly",\n"url": " http://statuscode.org/ ",\n"text": "A weekly newsletter focusing on software development, infrastructure,\nthe server, performance, and the stack end of things."\n}\n}'

def jsonBody = slurper.parseText(body)

def get_response = WS.sendRequest(findTestObject('User/Route 2 - GET Single User', [('id') : '2']))

WS.verifyEqual(slurper.parseText(get_response.getResponseBodyContent()), jsonBody, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyElementPropertyValue(get_response, 'data.id', jsonBody.data.id, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyElementPropertyValue(get_response, 'data.email', jsonBody.data.email, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyElementPropertyValue(get_response, 'data.first_name', jsonBody.data.first_name, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyElementPropertyValue(get_response, 'data.last_name', jsonBody.data.last_name, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyElementPropertyValue(get_response, 'data.avatar', jsonBody.data.avatar, FailureHandling.CONTINUE_ON_FAILURE)