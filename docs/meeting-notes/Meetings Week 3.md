# TA Meeting

- Next week make sure to appoint a chairman way before the meeting so that there&#39;s an agenda in advance.
- Make sure ALL project files are in the repository. (docs-notes, docs-reviews folders)

- Recommended using GitLab boards, not 3rd party platforms like Trello.

# CTA Meeting

## Changes to the project requirements

- **Removed Accessibility** : No colour-blind mode &amp; report; No getting started guide.
- **Removed Open-Answer Polls** : Now the polls are multiple-choice only.
- **Removed Question Merging** : No merging questions, mods can just delete duplicate questions.
- **Removed Tags** : There&#39;s no more adding tags to questions system.
- **Removed Report-As-Spam** : No more report as spam button.

## Notes from meeting

- IP-bans should be temporary. So session specific for example.
- **Clarification** : Moderators and teachers are the same role. System shouldn&#39;t distinguish between them.
- Poll&#39;s don&#39;t have to be open answer anymore. Poll should be able to re-open after having closed, to take additional answers.
- You should also have quizzes. -\&gt; A multiple choice question that has a correct answer. (Polls are to gather information and therefore have no incorrect or correct answers.)
- Database saving questions after session is not **MUST HAVE** but **SHOULD HAVE**.
- Any TA should be able to run the system. In the README file there should be clarification on how to run. (what to fill into the terminal (and what to have installed)).
- Users should fill in a username placeholder before entering a session, so NO authentication but just to distinguish users.
- System should be able to restart without losing data. Even with unexpected crash it should be able to save data and restart without problems.
- After lecture is dismissed there should be OPEN and CLOSED lectures. OPEN still allows students to stay in lecture to ask additional questions. CLOSED lectures only allow moderators to still be present. This is so that after a lecture is over moderators should be able to export questions into a text file. -\&gt; CLOSED also kicks all students, to students the session appears to not exist anymore (and be completely over).

### Additional features

- Moderators should be able to reply to questions so that questions don&#39;t always have to be answered verbally. -\&gt; students are not able to reply to questions.
- A moderator picks a question when choosing to answer it which is visible to other moderators so that no two moderators are answering the same question at once.
- When a questions is answered it is marked as answered.
 - when answered verbally there should be a timestamp to what point in the lecture it is discussed.
 - when answered textually, only mark it, the answer is already there of course.
- Even if the lecture is closed the moderator should be able to reply to questions so that when the questions are exported the answers/replies are included.

### Feedback on backlog

- Students should not be able to edit their own questions.
- There should be a presenter view (&#39;zen mode&#39;) that is only available to moderators (intended for lecturer mainly) that only show the important questions.

### Feedback on design

- &#39;other stuff&#39; could contain polls and quizzes
- Presenter view should be super basic. As &#39;zen&#39; as possible. No sidebar, only popular questions with a button &#39;answered this question&#39; and &#39;discard question&#39; or something.
- Moderator and teacher are the same role and therefore the same screen, rights and layout.