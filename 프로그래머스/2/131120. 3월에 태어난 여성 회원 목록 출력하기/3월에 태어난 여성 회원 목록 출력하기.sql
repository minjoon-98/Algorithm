SELECT
    MEMBER_ID,
    MEMBER_NAME,
    GENDER,
    DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d') AS DATE_OF_BIRTH
FROM
    MEMBER_PROFILE
WHERE
    GENDER = 'W'
    AND TLNO IS NOT NULL
    AND EXTRACT(MONTH FROM DATE_OF_BIRTH) = 3
ORDER BY
    MEMBER_ID ASC;