<cfset arrayTools = createObject("java", "com.adampresley.cfpowertools.Factory").getArrayTools() />
<cfset stringTools = createObject("java", "com.adampresley.cfpowertools.Factory").getStringTools() />

<!---
	Let's emulate something linke Wheel of Fortune. The phrase below will
	be the correct answer. Using another power tool we will break it up
	into an array.
--->
<cfset correctAnswer = "CFPowerTools rocks hardcore" />
<cfset correctAnswerAsArray = stringTools.toArray(lCase(correctAnswer)) />

<strong>Correct Answer:</strong> <cfoutput>#correctAnswer#</cfoutput>
<br />


<!---
	Now let's pretend a player has guessed some letters. We will use the 
	intersect() method to determine which of those guessed letters are
	actually in our correct answer phrase.
--->
<cfset guess = [ "s", "t", "i", "p", "o" ] />
<cfset correctGuesses = arrayTools.intersect(correctAnswerAsArray, guess) />

<strong>Letters guessed:</strong>
<cfdump var="#guess#" />

<strong>Letters guessed correctly:</strong>
<cfdump var="#correctGuesses#" />