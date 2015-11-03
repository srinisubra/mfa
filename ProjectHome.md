# ABSTRACT #
The purpose of this project is to develop a secure multi-factor authentication API that would provide off-the-shelve components that application developers can configure and use in their own web-applications. The resulting web-application will thus provide multi-factor authentication capability with robust security measures to provide safeguards against the most common attacks on web-applications.

# MOTIVATION #
In the war of functionality versus security, the former wins more often. Security has always been viewed upon as an overhead or afterthought by software developers. Nevertheless, there is a growing awareness in developer community regarding security due to several attacks that have surfaced off late.

One of the key challenges in designing a web-application revolves around the problem of identity management.  Depending on the need, most websites have authentication mechanisms to identify a particular user and render services accordingly. Single factor authentication has been around for a while now. Yet it’s not enough for having any meaningful security. The solution is to have multi-factor authentication which may be something you have ( mobile phone, hardware token , etc ) , something you are ( biometric, etc ) or something you know ( passwords, secret question , etc ).

Our motivation for developing this API is to eliminate the need for application developers to know the intricate details of security implementation of multi-factor authentication (MFA) and rather focus on the core functionality of the application. Our design is somewhat analogous to Java Authentication and Authorization Services (JAAS) which provides API for performing common authentication and authorization tasks. Our scope is limited to web-applications that are built atop Java Enterprise Edition (JEE) technology. We have incorporated Spring MVC to make our API modular and easily configurable. With our API, the developers can add MFA to their existing web-applications just by making some configuration changes. This approach not just increases the productivity of a developer but also reduces the chances of security misconfiguration that renders web-applications vulnerable to attacks.