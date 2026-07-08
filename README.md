# YouTube Web Automation Testing

A robust, automated end-to-end (E2E) testing suite for YouTube's web platform. This project validates critical UI components, dynamic content sections (Films, Music, News), and sidebar navigation links using advanced selenium interactions and validation checkpoints.

## Tech Stack & Skills Used
* **Automation Framework** : Selenium WebDriver (Java/Python)
* **Testing Framework** : TestNG
* **Locator Strategy** : Dynamic XPath
* **Concepts Applied** : Soft Asserts, Explicit/Implicit Waits, JavaScript Executor (for horizontal scrolling)

---

## Automated Test Cases

The test suite covers four main modules on YouTube.com, executing the following validation workflows:

### 1. Navigation & Sidebar Verification
* Navigates to `YouTube.com` and asserts the current URL to ensure successful page load.
* Locates and clicks the **"About"** link at the bottom of the sidebar.
* Dynamically extracts and prints the text content from the landing screen to the console.

### 2. "Films" Tab Validation (Dynamic Scroller)
* Navigates to the **Films** category.
* Locates the **"Top Selling"** horizontal scroll section and scrolls to the extreme right to load dynamic cards.
* Applies **Soft Asserts** to verify:
  * If the movie is appropriately marked with an "A" (Mature) certification wrapper.
  * If the genre of the movie is correctly classified as either "Comedy" or "Animation".

### 3. "Music" Tab Metadata Extraction
* Moves to the **Music** section.
* Scrolls to the extreme right of the first featured shelf section.
* Extracts and prints the title of the final playlist card.
* Executes a **Soft Assert** to confirm that the playlist contains $\le 50$ total tracks.

### 4. "News" Tab Data Aggregation
* Navigates to the **News** tab.
* Scrapes the structural text layout for the first **3 "Latest News Posts"**, capturing:
  * Title/Header text
  * Main description body
* Tallies the total likes across all 3 posts and logs the final aggregated sum.

---
