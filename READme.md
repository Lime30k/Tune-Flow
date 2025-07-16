# Tune-Flow

Tune-Flow is a JavaFX-based desktop music player and playlist manager. It allows you to play local MP3 files, manage a song queue, save playlists, and rate tracks.

---

## Features

- Play MP3 files from your local library
- Song queue with add/remove and skip controls
- Playlist saving and loading
- Fuzzy search to find songs even with typos
- Per-song rating system (0–5 stars)
- Song metadata form with artist, genre, album, and moods
- Progress bar and time display

---

## Folder Structure

- `src/com/example/walter/` — JavaFX application code
- `data/` — MP3 and PNG song files
- `1listinit`, `1songinit`, etc. — simple file-based storage for playlists and songs

---

## Getting Started

### Requirements

- Java 17 or higher
- JavaFX SDK

### Running

1. Clone the repository:
git clone https://github.com/Lime30k/Tune-Flow.git
2. Open in your IDE and configure the JavaFX SDK.
3. Build and run the application.

---

## Usage

### Adding Songs

1. Place your `.mp3` and optional `.png` cover images in the `data/` folder.
2. Use the **Add Song** button in the app.
3. Fill in metadata such as name, artist, genre, moods, and rating.
4. Save to add the song to your library.

### Playlists

- Create, save, and load playlists using the playlist menu.
- Songs in a playlist will queue automatically.

### Queue Management

- Add songs to the queue.
- Skip or remove songs as needed.

---

## Notes

- All song and playlist data is stored in plain text files for easy editing and backup.
- The UI is built using JavaFX with a focus on simplicity and local file playback.

---

## License

MIT License. See [LICENSE](LICENSE) for details.
