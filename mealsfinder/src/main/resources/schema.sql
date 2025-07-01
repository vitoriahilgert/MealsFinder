DROP TABLE IF EXISTS post_service_tags;
DROP TABLE IF EXISTS post_food_tags;
DROP TABLE IF EXISTS post_environment_tags;
DROP TABLE IF EXISTS establishment_environment_tags;
DROP TABLE IF EXISTS establishment_service_tags;
DROP TABLE IF EXISTS establishment_food_tags;
DROP TABLE IF EXISTS client_disliked_food_tags;
DROP TABLE IF EXISTS client_liked_food_tags;
DROP TABLE IF EXISTS comment_likes;
DROP TABLE IF EXISTS saved_posts;
DROP TABLE IF EXISTS post_likes;
DROP TABLE IF EXISTS blocked_users;
DROP TABLE IF EXISTS follows;
DROP TABLE IF EXISTS user_activities;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS menu_items;
DROP TABLE IF EXISTS menu_categories;
DROP TABLE IF EXISTS establishments;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS service_tags;
DROP TABLE IF EXISTS food_tags;
DROP TABLE IF EXISTS environment_tags;

CREATE TABLE IF NOT EXISTS environment_tags
(
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS food_tags
(
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS service_tags
(
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id                    TEXT PRIMARY KEY,
    user_type             TEXT        NOT NULL CHECK (user_type IN ('client', 'establishment')),
    email                 TEXT UNIQUE NOT NULL,
    phone_number          TEXT,
    username              TEXT UNIQUE NOT NULL,
    password              TEXT        NOT NULL,
    profile_picture_url   TEXT,
    account_creation_date DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_account_confirmed  BOOLEAN     NOT NULL DEFAULT 0,
    confirmation_code     TEXT,
    bio                   TEXT
);

CREATE TABLE IF NOT EXISTS clients
(
    user_id TEXT PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS establishments
(
    user_id               TEXT PRIMARY KEY,
    cnpj                  TEXT UNIQUE NOT NULL,
    name                  TEXT        NOT NULL,
    establishment_type    TEXT        NOT NULL,
    is_delivery           BOOLEAN     NOT NULL DEFAULT 0,
    is_presencial         BOOLEAN     NOT NULL DEFAULT 0,
    status                TEXT        NOT NULL DEFAULT 'PENDENTE',
    rejections            INTEGER     NOT NULL DEFAULT 0 CHECK (rejections >= 0 AND rejections <= 2),
    address_cep           TEXT,
    address_city          TEXT,
    address_state         TEXT,
    address_neighbourhood TEXT,
    address_street        TEXT,
    address_number        TEXT,
    address_complement    TEXT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS menu_categories
(
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    establishment_id TEXT    NOT NULL,
    name             TEXT    NOT NULL,
    display_order    INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (establishment_id) REFERENCES establishments (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS menu_items
(
    id           TEXT PRIMARY KEY,
    category_id  INTEGER NOT NULL,
    name         TEXT    NOT NULL,
    description  TEXT,
    price        REAL    NOT NULL,
    image_url    TEXT,
    is_available BOOLEAN NOT NULL DEFAULT 1,
    FOREIGN KEY (category_id) REFERENCES menu_categories (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS posts
(
    id          TEXT PRIMARY KEY,
    user_id     TEXT     NOT NULL,
    description TEXT,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reviews
(
    post_id                   TEXT PRIMARY KEY,
    reviewed_establishment_id TEXT    NOT NULL,
    price_rate                REAL,
    food_rate                 REAL,
    establishment_rate        REAL,
    service_rate              REAL,
    delivery_rate             REAL,
    is_delivery_review        BOOLEAN NOT NULL DEFAULT 0,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    FOREIGN KEY (reviewed_establishment_id) REFERENCES establishments (user_id) ON DELETE SET NULL,
    CHECK (
        price_rate IS NOT NULL OR
        food_rate IS NOT NULL OR
        establishment_rate IS NOT NULL OR
        service_rate IS NOT NULL OR
        delivery_rate IS NOT NULL
        )
);

CREATE TABLE IF NOT EXISTS comments
(
    id                TEXT PRIMARY KEY,
    post_id           TEXT     NOT NULL,
    user_id           TEXT     NOT NULL,
    parent_comment_id TEXT,
    description       TEXT     NOT NULL,
    created_at        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (parent_comment_id) REFERENCES comments (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS images
(
    id               TEXT PRIMARY KEY,
    establishment_id TEXT,
    post_id          TEXT,
    url              TEXT     NOT NULL,
    image_type       TEXT     NOT NULL,
    created_at       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CHECK ((establishment_id IS NOT NULL AND post_id IS NULL) OR (establishment_id IS NULL AND post_id IS NOT NULL)),
    FOREIGN KEY (establishment_id) REFERENCES establishments (user_id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_activities
(
    id               TEXT PRIMARY KEY,
    user_id          TEXT     NOT NULL,
    action_type      TEXT     NOT NULL,
    entity_type      TEXT,
    entity_id        TEXT,
    timestamp        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    engagement_score REAL,
    context_json     TEXT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS follows
(
    follower_id  TEXT NOT NULL,
    following_id TEXT NOT NULL,
    PRIMARY KEY (follower_id, following_id),
    FOREIGN KEY (follower_id) REFERENCES clients (user_id) ON DELETE CASCADE,
    FOREIGN KEY (following_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS blocked_users
(
    blocker_id TEXT NOT NULL,
    blocked_id TEXT NOT NULL,
    PRIMARY KEY (blocker_id, blocked_id),
    FOREIGN KEY (blocker_id) REFERENCES clients (user_id) ON DELETE CASCADE,
    FOREIGN KEY (blocked_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS post_likes
(
    user_id TEXT NOT NULL,
    post_id TEXT NOT NULL,
    PRIMARY KEY (user_id, post_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS saved_posts
(
    user_id TEXT NOT NULL,
    post_id TEXT NOT NULL,
    PRIMARY KEY (user_id, post_id),
    FOREIGN KEY (user_id) REFERENCES clients (user_id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comment_likes
(
    user_id    TEXT NOT NULL,
    comment_id TEXT NOT NULL,
    PRIMARY KEY (user_id, comment_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (comment_id) REFERENCES comments (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS client_liked_food_tags
(
    client_id   TEXT    NOT NULL,
    food_tag_id INTEGER NOT NULL,
    PRIMARY KEY (client_id, food_tag_id),
    FOREIGN KEY (client_id) REFERENCES clients (user_id) ON DELETE CASCADE,
    FOREIGN KEY (food_tag_id) REFERENCES food_tags (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS client_disliked_food_tags
(
    client_id   TEXT    NOT NULL,
    food_tag_id INTEGER NOT NULL,
    PRIMARY KEY (client_id, food_tag_id),
    FOREIGN KEY (client_id) REFERENCES clients (user_id) ON DELETE CASCADE,
    FOREIGN KEY (food_tag_id) REFERENCES food_tags (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS establishment_food_tags
(
    establishment_id TEXT    NOT NULL,
    food_tag_id      INTEGER NOT NULL,
    PRIMARY KEY (establishment_id, food_tag_id),
    FOREIGN KEY (establishment_id) REFERENCES establishments (user_id) ON DELETE CASCADE,
    FOREIGN KEY (food_tag_id) REFERENCES food_tags (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS establishment_service_tags
(
    establishment_id TEXT    NOT NULL,
    service_tag_id   INTEGER NOT NULL,
    PRIMARY KEY (establishment_id, service_tag_id),
    FOREIGN KEY (establishment_id) REFERENCES establishments (user_id) ON DELETE CASCADE,
    FOREIGN KEY (service_tag_id) REFERENCES service_tags (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS establishment_environment_tags
(
    establishment_id   TEXT    NOT NULL,
    environment_tag_id INTEGER NOT NULL,
    PRIMARY KEY (establishment_id, environment_tag_id),
    FOREIGN KEY (establishment_id) REFERENCES establishments (user_id) ON DELETE CASCADE,
    FOREIGN KEY (environment_tag_id) REFERENCES environment_tags (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS post_environment_tags
(
    post_id            TEXT    NOT NULL,
    environment_tag_id INTEGER NOT NULL,
    PRIMARY KEY (post_id, environment_tag_id),
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    FOREIGN KEY (environment_tag_id) REFERENCES environment_tags (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS post_food_tags
(
    post_id     TEXT    NOT NULL,
    food_tag_id INTEGER NOT NULL,
    PRIMARY KEY (post_id, food_tag_id),
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    FOREIGN KEY (food_tag_id) REFERENCES food_tags (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS post_service_tags
(
    post_id        TEXT    NOT NULL,
    service_tag_id INTEGER NOT NULL,
    PRIMARY KEY (post_id, service_tag_id),
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    FOREIGN KEY (service_tag_id) REFERENCES service_tags (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_menu_categories_establishment_id ON menu_categories (establishment_id);
CREATE INDEX IF NOT EXISTS idx_menu_items_category_id ON menu_items (category_id);
CREATE INDEX IF NOT EXISTS idx_posts_user_id ON posts (user_id);
CREATE INDEX IF NOT EXISTS idx_reviews_reviewed_establishment_id ON reviews (reviewed_establishment_id);
CREATE INDEX IF NOT EXISTS idx_comments_post_id ON comments (post_id);
CREATE INDEX IF NOT EXISTS idx_comments_user_id ON comments (user_id);
CREATE INDEX IF NOT EXISTS idx_comments_parent_comment_id ON comments (parent_comment_id);
CREATE INDEX IF NOT EXISTS idx_images_establishment_id ON images (establishment_id);
CREATE INDEX IF NOT EXISTS idx_images_post_id ON images (post_id);
CREATE INDEX IF NOT EXISTS idx_user_activities_user_id ON user_activities (user_id);
CREATE INDEX IF NOT EXISTS idx_user_activities_entity_id ON user_activities (entity_id);
CREATE INDEX IF NOT EXISTS idx_follows_following_id ON follows (following_id);
CREATE INDEX IF NOT EXISTS idx_post_likes_post_id ON post_likes (post_id);
CREATE INDEX IF NOT EXISTS idx_comment_likes_comment_id ON comment_likes (comment_id);