<cfinclude template="../head.cfm" />

<h1>ArrayTools.intersect</h1>
<hr />

<p>
	The <strong>intersect</strong> method will take two arrays and compare their values
	and return to you an array of the items that are the same between them.
</p>

<p>
	In this example we will pretend we have a Wheel of Fortune style game. Given the
	following phrase <em>CF PowerTools rocks hardcore</em>, and the following letters
	guessed from our imaginary player, <strong>[ "s", "t", "i", "p", "o" ]</strong>, this method
	will tell us which letters were guessed correctly.
</p>

<!---
	Let's emulate something linke Wheel of Fortune. The phrase below will
	be the correct answer. Using another power tool we will break it up
	into an array.
--->
<cfset correctAnswer = "CF PowerTools rocks hardcore" />
<cfset correctAnswerAsArray = application.stringTools.toArray(lCase(correctAnswer)) />

<!---
	Now let's pretend a player has guessed some letters. We will use the 
	intersect() method to determine which of those guessed letters are
	actually in our correct answer phrase.
--->
<cfset guess = [ "s", "t", "i", "p", "o" ] />
<cfset correctGuesses = application.arrayTools.intersect(correctAnswerAsArray, guess) />


<section class="well">
	<p>
		Using the following lines of code we will find out how close our contestant is.

		<pre>
			&lt;cfset correctAnswer = "CF PowerTools rocks hardcore" /&gt;
			&lt;cfset correctAnswerAsArray = application.stringTools.toArray(lCase(correctAnswer)) /&gt;

			&lt;cfset guess = [ "s", "t", "i", "p", "o" ] /&gt;
			&lt;cfset correctGuesses = application.arrayTools.intersect(correctAnswerAsArray, guess) /&gt;
		</pre>
	</p>

	<cfdump var="#correctGuesses#" />
	<br />
	
	<p>
		Looks like they got 4 of 5 correct!
	</p>
</section>

<cfinclude template="../foot.cfm" />
